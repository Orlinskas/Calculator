package com.orlinskas.calculator.repository

import com.orlinskas.calculator.calculatorSceleton
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResponse
import com.orlinskas.calculator.model.CalculatorResultModel
import com.orlinskas.calculator.network.Api
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.platform.NetworkHandler

class SimpleCalculatorRepository(
    private val api: Api,
    private val networkHandler: NetworkHandler
) : Repository<CalculatorResponse, CalculatorResultModel> {

    suspend fun calculate(params: CalculatorRequest): Either<Failure, CalculatorResultModel> {
        return if (networkHandler.isConnected) {
            val defaultData = calculatorSceleton
            request(api.simpleCalculator(params), defaultData)
        } else {
            return Either.Left(Failure.NetworkConnection())
        }
    }

    override fun updateOrStoreLocally(result: Any?) {
       //
    }
}