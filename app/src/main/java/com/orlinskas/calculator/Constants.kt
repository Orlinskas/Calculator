package com.orlinskas.calculator

import com.orlinskas.calculator.data.model.CalculationResult
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.data.model.InputValues
import com.orlinskas.calculator.data.model.Product

const val BASE_URL = "https://icma.com.ua/api/"
const val HEADER_TYPE = "Content-type"
const val HEADER_ACCEPT = "Accept"
const val HEADER_VALUE = "application/json"
const val BASE_REQUEST = "base"

fun getDefaultSceleton(): CalculatorResultModel {
    val products = listOf(
        Product(
            4,
            "https://icma.com.ua/storage/calculator-data/products/5250.jpg",
            "Євроконус Icma 16х2 3/4\" №101",
            "62.00",
            "5250",
            "248.00"
        ),
        Product(
            107,
            "https://icma.com.ua/storage/calculator-data/products/5250.jpg",
            "Труба GOLD-PEX Icma 16х2мм 200 м №P198",
            "25.00",
            "5306",
            "2675.00"
        ),
        Product(
            1,
            "https://icma.com.ua/storage/calculator-data/products/5250.jpg",
            "Насос Grundfos Icma 25/40-60 №P326",
            "3792.00",
            "15560",
            "3792.00"
        ),
        Product(
            14,
            "https://icma.com.ua/storage/calculator-data/products/5250.jpg",
            "Демпферна стрічка 8 мм (50м.п.)",
            "4.00",
            "8836",
            "400.00"
        ),
        Product(
            1,
            "https://icma.com.ua/storage/calculator-data/products/5250.jpg",
            "Шафа колекторна 480x580х110 (внутрішній) №1 \"Україна\"",
            "525.00",
            "15371",
            "525.00"
        )
    )

    val result = CalculationResult(2, 107)

    val values = InputValues(
        2f,
        false,
        2f,
        true,
        10,
        5f
    )

    return CalculatorResultModel(0, result, values, products, null, "20 546.00", null)
}

enum class ApiResponse(val code: Int) {
    OK(200),
    INVALID_INPUT(422),
    INVALID_INPUT_WITH_FIELD(423)
}

enum class Isolation(val value: String) {
    OPT("opt"),
    ECO("eco")
}

enum class LocalizationEnum(val code: String) {
    RU("ru"),
    UA("ua")
}

const val SERIALIZABLE_CALCULATOR_RESULT_MODEL = "resultModel"