package com.androidalliance.mynilam.ui.screen.mainfeatures.book.viewmodel

data class BookFormState(
    val bookId: Int = 0,
    val title: String = "",
    val titleError: String? = null,
    val author: String = "",
    val authorError: String? = null,
    val type: String = "",
    val typeError: String? = null,
    val genre: String = "",
    val genreError: String? = null,
    val publication: String = "",
    val publicationError: String? = null,
    val publicationYear: String = "",
    val publicationYearError: String? = null
)