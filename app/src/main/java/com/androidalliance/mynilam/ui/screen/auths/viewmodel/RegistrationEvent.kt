package com.androidalliance.mynilam.ui.screen.auths.viewmodel

sealed class RegistrationEvent {
    data class EmailChanged(val email: String) : RegistrationEvent()
    data class UsernameChanged(val username: String) : RegistrationEvent()
    data class PasswordChanged(val password: String) : RegistrationEvent()
    data class ConfirmPasswordChanged(val password: String, val confirmPassword: String) : RegistrationEvent()
    object Submit : RegistrationEvent()
}