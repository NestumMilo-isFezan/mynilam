package com.androidalliance.mynilam.ui.screen.auths.viewmodel

data class AuthFormState(
    val email: String = "",
    val emailError: String? = null,
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null
)