package com.androidalliance.mynilam.domain.use_case.auth_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidatePassword {
    fun execute(password: String) : ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must be at least 8 characters!"
            )
        }

        val containsLettersAndDigit = password.any { it.isDigit() }
                && password.any { it.isLetter() }
        if(!containsLettersAndDigit) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must contain at least one letter and digit!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}