package com.androidalliance.mynilam.ui.screen.auths.viewmodel

import com.androidalliance.mynilam.data.models.User

sealed class LoginEvent{
    data class UsernameChanged(val username: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Submit : LoginEvent()
}
