package com.androidalliance.mynilam.domain.use_case.book_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidatePublication {
    fun execute(publication: String) : ValidationResult {
        if (publication.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The publication field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}