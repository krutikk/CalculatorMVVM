package com.example.calculator.data


class Validator {
    fun validate(expression: String): Boolean {
        if (invalidStart(expression)) return false
        if (invalidEnd(expression)) return false

        if (hasMultipleDecimals(expression)) return false
        return true
    }

    private fun invalidStart(expression: String): Boolean {
        return when {
            expression.startsWith("+") -> true
            expression.startsWith("-") -> true
            expression.startsWith("*") -> true
            expression.startsWith("/") -> true
            expression.startsWith(".") -> true
            else -> false
        }
    }

    private fun isConcurrentDecimal(current: Char, next: Char): Boolean {
        if (current.toString() == "." && next.toString() == ".") {
            return true
        }
        return false
    }

    private fun hasMultipleDecimals(expression: String): Boolean {
        expression.indices
            .forEach {
                if (it < expression.lastIndex) {
                    if (isConcurrentDecimal(expression[it], expression[it + 1])) {
                        return true
                    }
                }
            }

        return false
    }

    private fun invalidEnd(expression: String): Boolean {
        return when {
            expression.endsWith("+") -> true
            expression.endsWith("-") -> true
            expression.endsWith("*") -> true
            expression.endsWith("/") -> true
            expression.endsWith(".") -> true
            else -> false
        }
    }
}