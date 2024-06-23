package com.androidalliance.mynilam.domain.use_case.book_validation

import android.util.Patterns
import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateAuthor {
    fun execute(author: String) : ValidationResult {
        if (author.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The author field must be fill in!"
            )
        }
        val containsDigit = author.any { it.isDigit() }
        if(containsDigit) {
            return ValidationResult(
                successful = false,
                errorMessage = "The author name is invalid!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}