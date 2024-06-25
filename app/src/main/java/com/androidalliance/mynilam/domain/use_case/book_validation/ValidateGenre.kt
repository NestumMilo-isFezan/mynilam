package com.androidalliance.mynilam.domain.use_case.book_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateGenre {
    fun execute(genre: String) : ValidationResult {
        if (genre.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The book genre field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}