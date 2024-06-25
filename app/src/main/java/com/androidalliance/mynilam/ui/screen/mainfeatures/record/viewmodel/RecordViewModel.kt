package com.androidalliance.mynilam.ui.screen.mainfeatures.record.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidalliance.mynilam.data.models.ReadingMaterial
import com.androidalliance.mynilam.data.models.Record
import com.androidalliance.mynilam.data.repositories.interfaces.ReadingMaterialRepository
import com.androidalliance.mynilam.data.repositories.interfaces.RecordRepository
import com.androidalliance.mynilam.domain.use_case.record_validation.ValidateSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordViewModel(
    private val validateSummary: ValidateSummary = ValidateSummary(),
    private val recordRepository: RecordRepository,
    private val bookRepository: ReadingMaterialRepository
) : ViewModel() {

    var state by mutableStateOf(RecordFormState())

    val _summaryRecord = MutableStateFlow<List<Record>>(emptyList())
    val sharedSummaryRecord = _summaryRecord.asStateFlow()

    private val _recordInfo = MutableStateFlow<Record?>(null)
    val sharedRecordInfo = _recordInfo.asStateFlow()

    private val validationEventsChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventsChannel.receiveAsFlow()

    sealed class ValidationEvent {
        object Inserted : ValidationEvent()
        object Update : ValidationEvent()
        object Failure : ValidationEvent()
    }

    fun clearForm(){
        state = RecordFormState()
    }

    fun findThisUserRecords(userId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                recordRepository.getAllRecordByUser(userId)
                    .collect {
                        _summaryRecord.value = it
                    }
            }
        }
    }

    fun onCreateEvent(event: FormRecordEvent){
        when(event) {
            is FormRecordEvent.SummaryChanged -> {
                state = state.copy(summary = event.summary)
            }
            is FormRecordEvent.BookChanged ->{
                state = state.copy(selectedBook = event.book)
            }
            is FormRecordEvent.Submit -> {
                submit()
            }
            is FormRecordEvent.Update -> {
                submit(mode ="edit")
            }
        }
    }

    fun deleteRecord(record: Record){
        viewModelScope.launch {
            recordRepository.deleteRecord(record)
        }
    }

    fun getRecordState(recordId: Int, userId: Int){
        viewModelScope.launch {
            val record = withContext(Dispatchers.IO){
                recordRepository.getRecord(recordId, userId)
            }
            val book = withContext(Dispatchers.IO){
                bookRepository.getMaterialById(record?.materialId ?: 0)
            }
            _recordInfo.value = record
            if (record != null) {
                state = RecordFormState(
                    recordId = record.recordId,
                    summary = record.summary,
                    selectedBook = book?.title
                )
            }
        }
    }


    fun submit(mode: String ?= ""){
        val summaryResult = validateSummary.execute(state.summary)

        val hasError = listOf(
            summaryResult
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                summaryError = summaryResult.errorMessage
            )
            return
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
                    validationEventsChannel.send(ValidationEvent.Update)
                    val summary = Record(
                        recordId = state.recordId,
                        summary = state.summary,
                        userId = state.userId ?: 0,
                        materialId = findBook.materialId
                    )
                    withContext(Dispatchers.IO) {
                        recordRepository.updateRecord(summary)
                    }
                } else {
                    validationEventsChannel.send(ValidationEvent.Inserted)
                    val summary = Record(
                        summary = state.summary,
                        userId = state.userId ?: 0,
                        materialId = findBook.materialId
                    )
                    withContext(Dispatchers.IO) {
                        recordRepository.insertRecord(summary)
                    }
                }
            }
        }

    }

}