package com.androidalliance.mynilam.domain.use_case.review_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateReview {
    fun execute(review: String) : ValidationResult {
        if (review.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The review field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}