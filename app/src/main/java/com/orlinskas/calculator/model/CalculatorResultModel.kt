package com.orlinskas.calculator.model

import java.io.Serializable

data class CalculatorResultModel(
    val calculation_result: CalculationResult?,
    val input_values: InputValues?,
    val products: List<Product>?,
    val ru: Location?,
    val total_sum: String?,
    val ua: Location?
): Serializable
