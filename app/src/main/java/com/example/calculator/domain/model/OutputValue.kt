package com.example.calculator.domain.model

sealed class OutputValue<out T : Any>  {
    data class SuccessOutput<out T : Any>(val result: T) : OutputValue<T>()
    data class ErrorOutput(val result: String) : OutputValue<Nothing>()
}