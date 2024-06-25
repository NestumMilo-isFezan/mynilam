package com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidalliance.mynilam.data.models.Review
import com.androidalliance.mynilam.data.repositories.interfaces.ReadingMaterialRepository
import com.androidalliance.mynilam.data.repositories.interfaces.ReviewRepository
import com.androidalliance.mynilam.domain.use_case.review_validation.ValidateReview
import com.androidalliance.mynilam.navigation.BottomTabItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewViewModel(
    private val validateReview: ValidateReview = ValidateReview(),
    private val reviewRepository: ReviewRepository,
    private val bookRepository: ReadingMaterialRepository
) : ViewModel() {
    var state by mutableStateOf(ReviewFormState())

    private var _reviewList = MutableStateFlow<List<Review>>(emptyList())
    val sharedReviewList = _reviewList.asStateFlow()

    private val _reviewInfo = MutableStateFlow<Review?>(null)
    val sharedReviewInfo = _reviewInfo.asStateFlow()

    private val validationEventsChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventsChannel.receiveAsFlow()

    sealed class ValidationEvent {
        object Inserted : ValidationEvent()
        object Update : ValidationEvent()
        object Failure : ValidationEvent()
    }

    fun clearForm(){
        state = ReviewFormState()
    }

    fun onCreateEvent(event: ReviewFormEvent){
        when(event) {
            is ReviewFormEvent.ReviewChanged -> {
                state = state.copy(review = event.review)
            }

            is ReviewFormEvent.BookChanged -> {
                state = state.copy(selectedBook = event.book)
            }
            is ReviewFormEvent.RatingChanged ->{
                state = state.copy(rating = event.rating)
            }

            is ReviewFormEvent.Submit -> {
                submit()
            }

            is ReviewFormEvent.Update -> {
                submit(mode ="edit")
            }
        }
    }

    fun findThisUserReview(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                reviewRepository.getAllReviews()
                    .collect {
                        _reviewList.value = it
                    }
            }
        }
    }

    fun deleteReview(review: Review){
        viewModelScope.launch {
            reviewRepository.deleteReview(review)
        }
    }

    fun getReviewState(reviewId: Int, userId: Int){
        viewModelScope.launch {
            val review = withContext(Dispatchers.IO){
                reviewRepository.getReviewById(reviewId)
            }
            val book = withContext(Dispatchers.IO){
                bookRepository.getMaterialById(review?.materialId ?: 0)
            }
            _reviewInfo.value = review
            if (review != null) {
                state = ReviewFormState(
                    reviewId = review.reviewId,
                    review = review.comment,
                    rating = review.star,
                    selectedBook = book?.title
                )
            }
        }
    }

    fun submit (mode: String ?= ""){
        val reviewResult = validateReview.execute(state.review)

        val hasError = listOf(
            reviewResult
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                reviewError = reviewResult.errorMessage
            )
        }
        viewModelScope.launch{
            val bookName = state.selectedBook
            val findBook = withContext(Dispatchers.IO){
                bookRepository.getMaterialByTitle(bookName?:"")
            }
            if(findBook == null){
                validationEventsChannel.send(ValidationEvent.Failure)
            }
            else {
                if (mode == "edit") {
                    val review = Review(
                        reviewId = state.reviewId,
                        comment = state.review,
                        star = state.rating,
                        userId = state.userId ?: 0,
                        materialId = findBook.materialId
                    )
                    validationEventsChannel.send(ValidationEvent.Update)
                    withContext(Dispatchers.IO) {
                        reviewRepository.updateReview(review)
                    }
                }
                else{
                    val review = Review(
                        comment = state.review,
                        star = state.rating,
                        userId = state.userId ?: 0,
                        materialId = findBook.materialId
                    )
                    validationEventsChannel.send(ValidationEvent.Inserted)
                    withContext(Dispatchers.IO) {
                        reviewRepository.insertReview(review)
                    }
                }
            }
        }

    }

}