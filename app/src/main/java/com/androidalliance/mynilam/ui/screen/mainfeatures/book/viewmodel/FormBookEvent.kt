package com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel


sealed class FormBookEvent {
    data class TitleChanged(val title: String) : FormBookEvent()
    data class AuthorChanged(val author: String) : FormBookEvent()
    data class PublishedChanged(val publisher: String) : FormBookEvent()
    data class YearChanged(val year: String) : FormBookEvent()
    data class GenreChanged(val genre: String) : FormBookEvent()
    data class TypeChanged(val type: String) : FormBookEvent()
    object Submit : FormBookEvent()
    object Update : FormBookEvent()
}