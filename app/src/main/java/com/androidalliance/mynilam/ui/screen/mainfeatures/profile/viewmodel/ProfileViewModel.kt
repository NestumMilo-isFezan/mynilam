package com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.repositories.interfaces.ProfileRepository
import com.androidalliance.mynilam.domain.use_case.profile_validation.ValidateFirstName
import com.androidalliance.mynilam.domain.use_case.profile_validation.ValidateLastName
import com.androidalliance.mynilam.domain.use_case.profile_validation.ValidateMotto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateMotto: ValidateMotto = ValidateMotto(),
    private val profileRepository: ProfileRepository
) : ViewModel() {
    //
    var state by mutableStateOf(ProfileFormState())
    private val _profileState = MutableStateFlow<Profile?>(null)
    val sharedProfileState = _profileState.asStateFlow()

    fun clearForm(){
        state = ProfileFormState()
    }

    private val validationEventsChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventsChannel.receiveAsFlow()

    fun onCreateEvent(event: ProfileFormEvent){
        when(event){
            is ProfileFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is ProfileFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is ProfileFormEvent.MottoChanged -> {
                state = state.copy(motto = event.motto)
            }
            ProfileFormEvent.Submit -> {
                submit()
            }
        }
    }

    sealed class ValidationEvent {
        object Updated : ValidationEvent()
        object Failure : ValidationEvent()
    }

    fun getProfileState(userId: Int){
        viewModelScope.launch {
            val profile = withContext(Dispatchers.IO) {
                profileRepository.getProfile(userId)
            }
            _profileState.value = profile
            if (profile != null) {
                state = ProfileFormState(
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    motto = profile.motto,
                    userId = profile.userId,
                    profileId = profile.profileId
                )
            }
        }
    }

    fun submit(){
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val mottoResult = validateMotto.execute(state.motto)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            mottoResult
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                mottoError = mottoResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            val profile = Profile(
                firstName = state.firstName,
                lastName = state.lastName,
                motto = state.motto,
                userId = state.userId,
                profileId = state.profileId
            )
            validationEventsChannel.send(ValidationEvent.Updated)
            withContext(Dispatchers.IO) {
                profileRepository.updateProfile(profile)
            }
        }
    }

}