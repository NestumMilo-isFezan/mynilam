package com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel

import com.androidalliance.mynilam.data.models.ReadingMaterial

sealed class FormRecordEvent {
    data class SummaryChanged(val summary: String) : FormRecordEvent()
    data class BookChanged(val book: String) : FormRecordEvent()
    object Submit : FormRecordEvent()
    object Update : FormRecordEvent()
}