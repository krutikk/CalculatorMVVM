package com.example.calculator.core.usecase

interface Usecase<in Params, out Type> {

    fun execute(params: Params): Type

    object None
}