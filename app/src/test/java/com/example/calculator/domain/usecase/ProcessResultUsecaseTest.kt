package com.example.calculator.domain.usecase

import com.example.calculator.data.model.Expression
import com.example.calculator.domain.data.ICalculator
import com.example.calculator.domain.data.IValidator
import com.example.calculator.domain.model.OutputValue
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProcessResultUsecaseTest {

    @MockK
    lateinit var calculator: ICalculator

    @MockK
    lateinit var validator: IValidator

    lateinit var useCase: ProcessResultUsecase

    val EXPRESSION = "2+2"
    val ANSWER = "4"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = ProcessResultUsecase(validator, calculator)
    }

    @Test
    fun `on success result`() = runBlockingTest {
        coEvery { validator.validate(EXPRESSION) } returns true
        coEvery { calculator.calculate(EXPRESSION) } returns flowOf(Expression("4", true))

        val result = useCase.execute(EXPRESSION)

        assertEquals(OutputValue.SuccessOutput(ANSWER), result.first())
    }

    @Test
    fun `on error result`() = runBlockingTest {
        coEvery { validator.validate(EXPRESSION) } returns true
        coEvery { calculator.calculate(EXPRESSION) } throws ArithmeticException("/ by zero")

        val result = useCase.execute(EXPRESSION)

        verify(exactly = 1) { validator.validate(EXPRESSION) }
        assertEquals(OutputValue.ErrorOutput("Error"), result.first())
    }

    @Test
    fun `on valid fail result`() = runBlockingTest {
        coEvery { validator.validate(EXPRESSION) } returns false

        val result = useCase.execute(EXPRESSION)

        verify { calculator.calculate(EXPRESSION) wasNot Called }
        assertEquals(OutputValue.ErrorOutput("Error"), result.first())
    }
}