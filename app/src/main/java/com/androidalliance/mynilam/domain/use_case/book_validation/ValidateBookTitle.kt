package com.androidalliance.mynilam.domain.use_case.book_validation

import android.util.Patterns
import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateBookTitle {
    fun execute(title: String) : ValidationResult {
        if (title.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The book title field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}