package com.orlinskas.calculator.model

data class CalculatorRequest(
    val form: Form,
    val type: String
)

data class Form(
    val distance: String,
    val insulation: String,
    val length: String,
    val regulation: String,
    val step: String,
    val width: String
)