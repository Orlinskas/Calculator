package com.orlinskas.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orlinskas.calculator.data.Tables
import com.orlinskas.calculator.network.response.CalculationResult
import com.orlinskas.calculator.network.response.InputValues
import com.orlinskas.calculator.network.response.Localization
import com.orlinskas.calculator.network.response.Product
import java.io.Serializable

@Entity(tableName = Tables.CALCULATOR_RESULT_TABLE)
data class CalculatorResultModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val calculationResult: CalculationResult?,
    val inputValues: InputValues?,
    val products: List<Product>?,
    val ru: Localization?,
    val totalSum: String?,
    val ua: Localization?
): Serializable
