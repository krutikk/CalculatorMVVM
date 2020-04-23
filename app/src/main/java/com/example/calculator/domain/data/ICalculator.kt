package com.example.calculator.domain.data

import com.example.calculator.data.model.Expression
import kotlinx.coroutines.flow.Flow

interface ICalculator {
    fun calculate(expression: String) : Flow<Expression>
}