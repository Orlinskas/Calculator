package com.orlinskas.calculator.model

import java.io.Serializable

data class CalculatorResultModel(
    val calculationResult: CalculationResult?,
    val inputValues: InputValues?,
    val products: List<Product>?,
    val ru: Localization?,
    val totalSum: String?,
    val ua: Localization?
): Serializable
