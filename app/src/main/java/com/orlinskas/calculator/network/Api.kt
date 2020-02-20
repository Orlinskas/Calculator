package com.orlinskas.calculator.network

import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.SimpleCalculatorResponse
import com.orlinskas.calculator.model.container.ResponseData
import com.orlinskas.calculator.model.container.ResponseWithListData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("calculator")
    suspend fun simpleCalculator(@Body calculatorRequest: CalculatorRequest): List<SimpleCalculatorResponse>
}