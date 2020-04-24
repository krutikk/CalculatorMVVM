package com.example.calculator.data.model

import java.lang.IllegalArgumentException

data class Operator(val operatorValue: String) {
    val isFirst: Boolean = checkPriority(operatorValue)

    private fun checkPriority(operatorValue: String): Boolean {
        return when (operatorValue) {
            "*" -> true
            "/" -> true
            "+" -> false
            "-" -> false
            else -> throw  IllegalArgumentException("Illegal OperatorDataModel.")
        }
    }
}