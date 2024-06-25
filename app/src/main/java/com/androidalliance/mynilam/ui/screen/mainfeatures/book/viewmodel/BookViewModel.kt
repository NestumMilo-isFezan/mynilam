package com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidalliance.mynilam.data.models.ReadingMaterial
import com.androidalliance.mynilam.data.repositories.interfaces.ReadingMaterialRepository
import com.androidalliance.mynilam.domain.use_case.book_validation.ValidateAuthor
import com.androidalliance.mynilam.domain.use_case.book_validation.ValidateBookTitle
import com.androidalliance.mynilam.domain.use_case.book_validation.ValidateGenre
import com.androidalliance.mynilam.domain.use_case.book_validation.ValidatePubYear
import com.androidalliance.mynilam.domain.use_case.book_validation.ValidatePublication
import com.androidalliance.mynilam.domain.use_case.book_validation.ValidateType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(
    private val validateBookTitle: ValidateBookTitle = ValidateBookTitle(),
    private val validateAuthor: ValidateAuthor = ValidateAuthor(),
    private val validateType: ValidateType = ValidateType(),
    private val validateGenre: ValidateGenre = ValidateGenre(),
    private val validatePublication: ValidatePublication = ValidatePublication(),
    private val validatePublicationYear: ValidatePubYear = ValidatePubYear(),
    private val bookRepository: ReadingMaterialRepository
) : ViewModel() {

    var state by mutableStateOf(BookFormState())

    private val validationEventsChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventsChannel.receiveAsFlow()

    val _bookState = MutableStateFlow<ReadingMaterial?>(null)
    val sharedBookState = _bookState.asStateFlow()

    private val _books = MutableStateFlow<List<ReadingMaterial>>(emptyList())
    val sharedBooks = _books.asStateFlow()

    private fun fetchBooks(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                bookRepository.getAllMaterials().collect{
                    _books.value = it
                }
            }
        }
    }

    init {
        fetchBooks()
    }

    sealed class ValidationEvent {
        object Inserted : ValidationEvent()
        object Update : ValidationEvent()
        object Failure : ValidationEvent()
    }

    fun clearForm(){
        state = BookFormState()
    }

    fun onCreateEvent(event: FormBookEvent){
        when(event){
            is FormBookEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }
            is FormBookEvent.AuthorChanged -> {
                state = state.copy(author = event.author)
            }
            is FormBookEvent.TypeChanged -> {
                state = state.copy(type = event.type)
            }
            is FormBookEvent.GenreChanged -> {
                state = state.copy(genre = event.genre)
            }
            is FormBookEvent.PublishedChanged -> {
                state = state.copy(publication = event.publisher)
            }
            is FormBookEvent.YearChanged -> {
                state = state.copy(publicationYear = event.year)
            }
            is FormBookEvent.Submit -> {
                submit()
            }
            is FormBookEvent.Update -> {
                submit(mode = "edit")
            }
        }
    }

    fun deleteBook(book: ReadingMaterial){
        viewModelScope.launch {
            bookRepository.deleteMaterial(book)
        }
    }


    suspend fun getBookInfoById(bookId: Int) :ReadingMaterial?{
        return withContext(Dispatchers.IO){
            bookRepository.getMaterialById(bookId)
        }
    }

    fun getBookState(bookId: Int){
        viewModelScope.launch {
            val book = withContext(Dispatchers.IO){
                bookRepository.getMaterialById(bookId)
            }
            _bookState.value = book
            if (book != null) {
                state = BookFormState(
                    bookId = book.materialId,
                    title = book.title,
                    author = book.author,
                    type = book.type,
                    genre = book.genre,
                    publication = book.publication,
                    publicationYear = book.publicationYear.toString()
                )
            }
        }
    }

    private fun submit(mode: String ?= ""){
        val titleResult = validateBookTitle.execute(state.title)
        val authorResult = validateAuthor.execute(state.author)
        val typeResult = validateType.execute(state.type)
        val genreResult = validateGenre.execute(state.genre)
        val publicationResult = validatePublication.execute(state.publication)
        val publicationYearResult = validatePublicationYear.execute(state.publicationYear)

        val hasError = listOf(
            titleResult,
            authorResult,
            typeResult,
            genreResult,
            publicationResult,
            publicationYearResult
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                titleError = titleResult.errorMessage,
                authorError = authorResult.errorMessage,
                typeError = typeResult.errorMessage,
                genreError = genreResult.errorMessage,
                publicationError = publicationResult.errorMessage,
                publicationYearError = publicationYearResult.errorMessage
            )
            return
        }
        viewModelScope.launch{
            if(mode == "edit"){
                validationEventsChannel.send(ValidationEvent.Update)
                val book = ReadingMaterial(
                    materialId = state.bookId,
                    title = state.title,
                    author = state.author,
                    type = state.type,
                    genre = state.genre,
                    publication = state.publication,
                    publicationYear = state.publicationYear.toInt()
                )
                withContext(Dispatchers.IO) {
                    bookRepository.updateMaterial(book)
                }
            }
            else{
                val book = ReadingMaterial(
                    title = state.title,
                    author = state.author,
                    type = state.type,
                    genre = state.genre,
                    publication = state.publication,
                    publicationYear = state.publicationYear.toInt()
                )
                val bookExist = withContext(Dispatchers.IO){
                    bookRepository.getMaterialByTitle(book.title)
                }
                if(bookExist == null){
                    validationEventsChannel.send(ValidationEvent.Failure)
                }
                validationEventsChannel.send(ValidationEvent.Inserted)
                withContext(Dispatchers.IO) {
                    bookRepository.insertMaterial(book)
                }
            }

        }
    }
}