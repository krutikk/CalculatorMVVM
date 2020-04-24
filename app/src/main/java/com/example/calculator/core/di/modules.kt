package com.example.calculator.core.di

import com.example.calculator.data.Calculator
import com.example.calculator.data.Validator
import com.example.calculator.domain.usecase.ProcessResultUsecase
import com.example.calculator.ui.main.CalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val appModule = module {

    viewModel {
        CalculatorViewModel(get())
    }

    factory {
        ProcessResultUsecase(
            get(), get()
        )
    }

    factory {
        Validator()
    }

    factory {
        Calculator()
    }
}


val applicationModules = listOf(appModule)