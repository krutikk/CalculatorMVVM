package com.example.calculator.data

import com.example.calculator.data.model.Expression
import com.example.calculator.domain.data.ICalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Calculator : ICalculator {
    override fun calculate(expression: String): Flow<Expression> {
        return flow {
            emit(Expression("", true))
        }
    }
}