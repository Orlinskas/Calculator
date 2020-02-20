package com.orlinskas.calculator.repository

import com.orlinskas.calculator.getDefaultSceleton
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import com.orlinskas.calculator.network.Api
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.platform.NetworkHandler

class SimpleCalculatorRepository(
    private val api: Api,
    private val networkHandler: NetworkHandler
) {

    suspend fun calculate(params: CalculatorRequest): Either<Failure, List<CalculatorResultModel>> {
        return if (networkHandler.isConnected) {
            val defaultData = listOf(getDefaultSceleton())
            try {
                val response = api.simpleCalculator(params)
                Either.Right(response.map { it.convert() })
            } catch (e: Exception) {
                Either.Right(defaultData)
            }
        } else {
            Either.Left(Failure.NetworkConnection())
        }
    }

}