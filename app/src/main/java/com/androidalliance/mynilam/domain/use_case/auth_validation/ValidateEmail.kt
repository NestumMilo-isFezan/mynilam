package com.androidalliance.mynilam.domain.use_case.auth_validation

import android.util.Patterns
import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateEmail {
    fun execute(email: String) : ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email field must be fill in!"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email address is invalid!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}