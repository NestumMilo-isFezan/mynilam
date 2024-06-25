package com.androidalliance.mynilam.ui.screen.mainfeatures.profile.viewmodel

sealed class ProfileFormEvent {
    data class FirstNameChanged(val firstName: String) : ProfileFormEvent()
    data class LastNameChanged(val lastName: String) : ProfileFormEvent()
    data class MottoChanged(val motto: String) : ProfileFormEvent()
    object Submit : ProfileFormEvent()
}