package com.orlinskas.calculator.model.test

data class CalculatorResponseTest(
    val calculation_result: CalculationResult,
    val input_values: InputValues,
    val products: List<Product>,
    val ru: Ru,
    val total_sum: String,
    val ua: Ua
)