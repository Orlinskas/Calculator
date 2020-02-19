package com.orlinskas.calculator

import com.orlinskas.calculator.model.CalculatorResultModel

class Constants

const val BASE_URL = "https://icma.com.ua/api/"

public val calculatorSceleton = CalculatorResultModel(null, null, null, null, null, null)

enum class ApiResponse(val code: Int) {
    OK(200),
    OK_WITH_DATA(201),
    INVALID_TOKEN(401),
    DEACTIVATED(403),
    INVALID_INPUT(406),
    INVALID_DATA(432),
    NOT_FOUND(404),
    CONFLICT_TIME(409),
    LOCAL(999)
}