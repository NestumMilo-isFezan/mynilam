package com.androidalliance.mynilam.domain.use_case.auth_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateConfirmPassword {
    fun execute(password: String, confirmPassword: String) : ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password don't match!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}