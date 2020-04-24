package com.example.calculator

import android.app.Application
import com.example.calculator.core.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CalculatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CalculatorApp)
            androidLogger()
            modules(applicationModules)
        }
    }
}