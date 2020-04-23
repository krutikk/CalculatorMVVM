package com.example.calculator.domain.data

interface IValidator {
    fun validate(expression: String) : Boolean
}