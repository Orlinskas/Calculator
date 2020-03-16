package com.orlinskas.calculator.network.request

data class CalculatorRequest(
    val form: Form,
    val type: String
)

data class Form(
    val distance: String,
    val insulation: String,
    val length: String,
    val regulation: Boolean,
    val step: String,
    val width: String
)