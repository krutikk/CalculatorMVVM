package com.example.calculator.data

import com.example.calculator.data.model.Digit
import com.example.calculator.data.model.Expression
import com.example.calculator.data.model.Operator
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class CalculatorTest {
    //Test objet
    private val calculator = Calculator()

    companion object {
        const val ADD_EXPRESSION = "3+2"
        const val ADD_RESULT = "5.0"

        const val MULTIPLE_OPERATOR_EXPRESSION = "4+3-2*1+4"
        const val MULTIPLE_ANSWER = "9.0"
        val DIGITS = listOf(
            Digit("4"),
            Digit("3"),
            Digit("2"),
            Digit("1"),
            Digit("4")
        )
        const val MULTIPLE_OPERATOR_EXPRESSION_WITH_DOT = "-4.5+3-2*1+4"
        val DIGITSWITHDOT = listOf(
            Digit("-4.5"),
            Digit("3"),
            Digit("2"),
            Digit("1"),
            Digit("4")
        )
        val OPERATORS = mutableListOf(
            Operator("+"),
            Operator("-"),
            Operator("*"),
            Operator("+")
        )
    }

    @Test
    fun `retrieve operators success`() {
        val operatorDataModels: List<Operator> =
            calculator.getOperators(MULTIPLE_OPERATOR_EXPRESSION)
        assertEquals(OPERATORS, operatorDataModels)
    }

    @Test
    fun `retrieve digits success`() {
        val digits: List<Digit> =
            calculator.getDigits(MULTIPLE_OPERATOR_EXPRESSION)
        assertEquals(DIGITS, digits)
    }

    @Test
    fun `retrieve digits with dot success`() {
        val digits: List<Digit> =
            calculator.getDigits(MULTIPLE_OPERATOR_EXPRESSION_WITH_DOT)
        assertEquals(DIGITSWITHDOT, digits)
    }

    @Test
    fun `calculate simple expression`() = runBlockingTest {
        val result = calculator.calculate(ADD_EXPRESSION)
        assertEquals(Expression(ADD_RESULT, true), result.first())
    }



    @Test
    fun `calculate complex expression`() = runBlockingTest {
        val result = calculator.calculate(MULTIPLE_OPERATOR_EXPRESSION)
        assertEquals(Expression(MULTIPLE_ANSWER, true), result.first())
    }
}