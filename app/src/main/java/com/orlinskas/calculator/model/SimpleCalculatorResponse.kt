package com.orlinskas.calculator.model

import ua.brander.core.simple.repository.Convertable
import java.io.Serializable

data class SimpleCalculatorResponse(
    val names: RowNames,
    val data: RowsData
): Convertable<CalculatorResultModel> {

    override fun convert(): CalculatorResultModel {
        return CalculatorResultModel(data.calculation_result, data.input_values, data.products, names.ru, data.total_sum, names.ua)
    }
}

data class RowsData(
    val calculation_result: CalculationResult,
    val input_values: InputValues,
    val products: List<Product>,
    val total_sum: String
): Serializable

data class CalculationResult(
    val quantity_contour: Int,
    val tube_length: Int
): Serializable

data class InputValues(
    val distance: Int,
    val insulation: Boolean,
    val length: Int,
    val regulation: Boolean,
    val step: Int,
    val width: Int
): Serializable

data class Product(
    val count: Int,
    val name: String,
    val price: String,
    val sku: String,
    val sum: String
): Serializable

data class RowNames(
    val ru: Localizations,
    val ua: Localizations
): Serializable

data class Localizations(
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