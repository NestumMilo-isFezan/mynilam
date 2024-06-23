package com.androidalliance.mynilam.domain.use_case.book_validation

import android.util.Patterns
import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidatePubYear {
    fun execute(pubYear: String) : ValidationResult {
        if (pubYear.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The book title field must be fill in!"
            )
        }
        val containsLetter = pubYear.any { it.isDigit() }
        if(containsLetter) {
            return ValidationResult(
                successful = false,
                errorMessage = "The publication year is invalid!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}