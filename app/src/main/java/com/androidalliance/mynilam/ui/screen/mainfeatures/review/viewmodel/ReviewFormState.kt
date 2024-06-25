package com.androidalliance.mynilam.ui.screen.mainfeatures.review.viewmodel

data class ReviewFormState(
    val reviewId: Int = 0,
    val review: String = "",
    val reviewError: String? = null,
    val selectedBook: String? = "",
    val selectedBookError: String? = null,
    val rating: Int = 0,
    val selectedBookId: Int? = null,
    val userId: Int? = null,
    val materialId: Int? = null
)