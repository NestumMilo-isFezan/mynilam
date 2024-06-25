package com.androidalliance.mynilam.domain.use_case.profile_validation

import com.androidalliance.mynilam.domain.use_case.ValidationResult

class ValidateFirstName {
    fun execute(fName: String) : ValidationResult {
        if (fName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The First Name field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}

class ValidateLastName{
    fun execute(lName: String) : ValidationResult {
        if (lName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The Last Name field must be fill in!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}