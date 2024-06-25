package com.androidalliance.mynilam.domain.use_case.book_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateType {
    fun execute(type: String) : ValidationResult {
        if (type.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The material type field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}