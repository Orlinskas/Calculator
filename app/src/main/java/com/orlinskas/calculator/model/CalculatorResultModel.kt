package com.orlinskas.calculator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CalculatorResultModel(
    val calculation_result: CalculationResult?,
    val input_values: InputValues?,
    val products: List<Product>?,
    val ru: Localizations?,
    val total_sum: String?,
    val ua: Localizations?
): Parcelable
