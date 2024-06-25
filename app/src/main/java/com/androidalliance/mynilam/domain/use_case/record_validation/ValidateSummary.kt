package com.androidalliance.mynilam.domain.use_case.record_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateSummary {
    fun execute(summary: String) : ValidationResult {
        if (summary.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The summary field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}