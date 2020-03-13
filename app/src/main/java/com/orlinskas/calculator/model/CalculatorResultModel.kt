package com.orlinskas.calculator.model

import java.io.Serializable

data class CalculatorResultModel(
    val calculationResult: CalculationResult?,
    val inputValues: InputValues?,
    val products: List<Product>?,
    val ru: Location?,
    val totalSum: String?,
    val ua: Location?
): Serializable
