package com.androidalliance.mynilam.domain.use_case.profile_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateMotto {
    fun execute(motto: String) : ValidationResult {
        if (motto.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The motto field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}