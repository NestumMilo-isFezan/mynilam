package com.androidalliance.mynilam.domain.use_case.auth_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateUsername {
    fun execute(username: String) : ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The username field must be fill in!"
            )
        }
        if (username.length < 5) {
            return ValidationResult(
                successful = false,
                errorMessage = "The username must be at least 5 characters!"
            )
        }
        val containSpace = username.any { it.isWhitespace() }
        if (containSpace) {
            return ValidationResult(
                successful = false,
                errorMessage = "The username can't contain space!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}