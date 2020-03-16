package com.orlinskas.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orlinskas.calculator.data.Tables
import java.io.Serializable

@Entity(tableName = Tables.CALCULATOR_RESULT_TABLE)
data class CalculatorResultModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val calculationResult: CalculationResult?,
    val inputValues: InputValues?,
    val product: List<Product>?,
    val ru: Localization?,
    val totalSum: String?,
    val ua: Localization?
): Serializable

data class Dictionary(
    val ru: Localization,
    val ua: Localization
): Serializable

data class Result(
    val calculationResult: CalculationResult,
    val inputValues: InputValues,
    val product: List<Product>,
    val totalSum: String
): Serializable

data class Localization(
    val count: String,
    val distance: String,
    val insulation: String,
    val length: String,
    val name: String,
    val price: String,
    val quantityContour: String,
    val regulation: String,
    val sku: String,
    val step: String,
    val sum: String,
    val totalSum: String,
    val tubeLength: String,
    val width: String
): Serializable

data class CalculationResult(
    val quantityContour: Int,
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
