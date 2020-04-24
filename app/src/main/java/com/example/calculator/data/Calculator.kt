package com.example.calculator.data

import androidx.annotation.VisibleForTesting
import com.example.calculator.data.model.Digit
import com.example.calculator.data.model.Expression
import com.example.calculator.data.model.Operator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Calculator {

    fun calculate(expression: String): Flow<Expression> {
        val operators: MutableList<Operator> = getOperators(expression)
        val digits: MutableList<Digit> = getDigits(expression)

        while (digits.size > 1) {
            val firstDigit = digits[0]
            val secondDigit = digits[1]
            val firstOperator = operators[0]

            if (firstOperator.isFirst ||
                operators.elementAtOrNull(1) == null ||
                !operators[1].isFirst
            ) {
                val result = Digit(evaluatePair(firstDigit, secondDigit, firstOperator))
                operators.remove(firstOperator)
                digits.remove(firstDigit)
                digits.remove(secondDigit)

                digits.add(0, result)
            } else {

                val thirdDigit = digits[2]
                val secondOperator = operators[1]
                val result = Digit(evaluatePair(secondDigit, thirdDigit, secondOperator))

                operators.remove(secondOperator)
                digits.remove(secondDigit)
                digits.remove(thirdDigit)

                digits.add(1, result)
            }
        }

        return flow {
            emit(Expression(digits[0].value, true))
        }
    }

    @VisibleForTesting
    internal fun getDigits(expression: String): MutableList<Digit> {
        val digits = expression.split("+", "-", "/", "*")
        val outPut: MutableList<Digit> = arrayListOf()

        //Kotlin's answer to enhanced for loop
        digits.indices.mapTo(outPut) {
            Digit(digits[it])
        }
        return outPut
    }

    @VisibleForTesting
    internal fun getOperators(expression: String): MutableList<Operator> {
        //this ugly stuff is called Regex; Regular ExpressionDataModel/
        //Basically saying split based on number or decimal numbers.
        val operators = expression.split("\\d+(?:\\.\\d+)?".toRegex())
            .filterNot { it == "" }
            .toMutableList()
        val outPut = mutableListOf<Operator>()

        operators.indices.mapTo(outPut) {
            Operator(operators[it])
        }
        return outPut
    }

    @VisibleForTesting
    internal fun evaluatePair(
        firstDigit: Digit,
        secondDigit: Digit,
        operator: Operator
    ): String {
        when (operator.operatorValue) {
            "+" -> return (firstDigit.value.toFloat().plus(secondDigit.value.toFloat()).toString())
            "-" -> return (firstDigit.value.toFloat().minus(secondDigit.value.toFloat()).toString())
            "/" -> return (firstDigit.value.toFloat().div(secondDigit.value.toFloat()).toString())
            "*" -> return (firstDigit.value.toFloat().times(secondDigit.value.toFloat()).toString())
        }
        throw  IllegalArgumentException("Illegal Operator.")
    }
}