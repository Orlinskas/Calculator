package com.orlinskas.calculator.network.response

import com.google.gson.annotations.SerializedName
import com.orlinskas.calculator.data.model.CalculatorResultModel
import ua.brander.core.simple.repository.Convertable
import java.io.Serializable

data class CalculatorResponse(
    val dictionary: Dictionary? = null,
    val result: Result? = null
): Convertable<CalculatorResultModel> {

    override fun convert(): CalculatorResultModel {
        return CalculatorResultModel(
            (0 until 999999999).random(), result?.calculationResult, result?.inputValues,
            result?.products, dictionary?.ru, result?.totalSum, dictionary?.ua
        )
    }
}

data class Dictionary(
    val ru: Localization,
    val ua: Localization
): Serializable

data class Result(
    @SerializedName("calculation_result")
    val calculationResult: CalculationResult,
    @SerializedName("input_values")
    val inputValues: InputValues,
    val products: List<Product>,
    @SerializedName("total_sum")
    val totalSum: String
): Serializable

data class Localization(
    val count: String,
    val distance: String,
    val insulation: String,
    val length: String,
    val name: String,
    val price: String,
    @SerializedName("quantity_contour")
    val quantityContour: String,
    val regulation: String,
    val sku: String,
    val step: String,
    val sum: String,
    @SerializedName("total_sum")
    val totalSum: String,
    @SerializedName("tube_length")
    val tubeLength: String,
    val width: String
): Serializable

data class CalculationResult(
    @SerializedName("quantity_contour")
    val quantityContour: Int,
    @SerializedName("tube_length")
    val tubeLength: Int
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