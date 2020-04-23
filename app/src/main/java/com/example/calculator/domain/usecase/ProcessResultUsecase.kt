package com.example.calculator.domain.usecase

import com.example.calculator.core.usecase.Usecase
import com.example.calculator.data.Calculator
import com.example.calculator.data.Validator
import com.example.calculator.domain.data.ICalculator
import com.example.calculator.domain.data.IValidator
import com.example.calculator.domain.model.OutputValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ProcessResultUsecase(private val validator: IValidator, private val calculator: ICalculator) :
    Usecase<String, Flow<OutputValue<String>>> {
    override fun execute(params: String): Flow<OutputValue<String>> {
        if (validator.validate(params)) {
            try {
                return calculator.calculate(params).map { exp ->
                    OutputValue.SuccessOutput(exp.value)
                }
            } catch (exception: Exception) {
                return flowOf(OutputValue.ErrorOutput("Error"))
            }


        } else {
            return flowOf(OutputValue.ErrorOutput("Error"))
        }
    }
}