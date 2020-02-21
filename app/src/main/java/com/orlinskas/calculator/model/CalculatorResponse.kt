package com.orlinskas.calculator.model

import ua.brander.core.simple.repository.Convertable
import java.io.Serializable

data class CalculatorResponse(
    val dictionary: Dictionary? = null,
    val result: Result ? = null
): Convertable<CalculatorResultModel> {

    override fun convert(): CalculatorResultModel {
        return CalculatorResultModel(result?.calculation_result, result?.input_values,
            result?.products, dictionary?.ru, result?.total_sum, dictionary?.ua)
    }
}

data class Dictionary(
    val ru: Location,
    val ua: Location
): Serializable

data class Result(
    val calculation_result: CalculationResult,
    val input_values: InputValues,
    val products: List<Product>,
    val total_sum: String
): Serializable

data class Location(
    val count: String,
    val distance: String,
    val insulation: String,
    val length: String,
    val name: String,
    val price: String,
    val quantity_contour: String,
    val regulation: String,
    val sku: String,
    val step: String,
    val sum: String,
    val total_sum: String,
    val tube_length: String,
    val width: String
): Serializable

data class CalculationResult(
    val quantity_contour: Int,
    val tube_length: Int
): Serializable

data class InputValues(
    val distance: Float,
    val insulation: Boolean,
    val length: Float,
    val regulation: Boolean,
    val step: Int,
    val width: Float
): Serializable

data class Product(
    val count: Int,
    val image: String,
    val name: String,
    val price: String,
    val sku: String,
    val sum: String
): Serializable

data class Errors(
    val errors: List<String>
): Serializable