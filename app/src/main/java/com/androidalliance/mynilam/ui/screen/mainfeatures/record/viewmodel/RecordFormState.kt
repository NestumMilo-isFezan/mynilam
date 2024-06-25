package com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel

import com.androidalliance.mynilam.data.models.ReadingMaterial

data class RecordFormState(
    val recordId: Int = 0,
    val summary: String = "",
    val summaryError: String? = null,
    val selectedBook: String? = "",
    val selectedBookError: String? = null,
    val selectedBookId: Int? = null,
    val userId: Int? = null
)