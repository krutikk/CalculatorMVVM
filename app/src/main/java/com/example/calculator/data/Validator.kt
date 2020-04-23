package com.example.calculator.data

import com.example.calculator.domain.data.IValidator

class Validator : IValidator {
    override fun validate(expression: String): Boolean {
        return true
    }
}