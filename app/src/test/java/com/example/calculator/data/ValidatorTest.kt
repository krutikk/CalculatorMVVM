package com.example.calculator.data

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ValidatorTest {
    //Test objet
    private val validator = Validator()


    @Test
    fun validExpressionTestOne() {
        assertTrue(validator.validate(ADD_OPERATOR_EXPRESSION))
    }

    @Test
    fun validExpressionTestTwo() {
        assertTrue(validator.validate(Companion.MULTIPLE_OPERATOR_EXPRESSION))
    }

    @Test
    fun invalidExpressionTestOne() {
        assertFalse(validator.validate(ADD_OPERATOR_END_EXPRESSION))
    }

    @Test
    fun invalidExpressionTestTwo() {
        assertFalse(validator.validate(ADD_OPERATOR_START_EXPRESSION))
    }


    @Test
    fun invalidExpressionTestFour() {
        assertFalse(validator.validate(DOT_EXPRESSION))
    }

    @Test
    fun validExpressionTestSix() {
        assertTrue(validator.validate(MINUS_OPERATOR_START_EXPRESSION))
    }

    @Test
    fun invalidExpressionTestFive() {
        assertFalse(validator.validate(MULTIPLE_DOT_EXPRESSION))
    }

    companion object {
        const val MULTIPLE_OPERATOR_EXPRESSION = "5+3-4*5/3"
        const val ADD_OPERATOR_EXPRESSION = "3+4"
        const val ADD_OPERATOR_END_EXPRESSION = "4+"
        const val MINUS_OPERATOR_START_EXPRESSION = "-4"
        const val ADD_OPERATOR_START_EXPRESSION = "+5"
        const val DOT_EXPRESSION = "."
        const val MULTIPLE_DOT_EXPRESSION = "3..4+2"
    }
}