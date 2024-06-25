package com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel

sealed class ReviewFormEvent{
    data class ReviewChanged(val review: String) : ReviewFormEvent()
    data class BookChanged(val book: String) : ReviewFormEvent()
    data class RatingChanged(val rating: Int) : ReviewFormEvent()
    object Submit : ReviewFormEvent()
    object Update : ReviewFormEvent()
}