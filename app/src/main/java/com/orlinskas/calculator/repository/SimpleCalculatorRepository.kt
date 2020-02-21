package com.orlinskas.calculator.repository

import com.orlinskas.calculator.getDefaultSceleton
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
): Repository<CalculatorResponse, CalculatorResultModel> {

    override fun updateOrStoreLocally(result: Any?) {
        //
    }

    suspend fun calculate(params: CalculatorRequest): Either<Failure, CalculatorResultModel> {
        return if (networkHandler.isConnected) {
            val defaultData = getDefaultSceleton()
            return try {
                request(api.simpleCalculator(params), defaultData)
            } catch (e: Exception) {
                Either.Left(Failure.ServerErrorWithDefaultData(Repository.NETWORK_NOT_AVAILABLE_ERROR_CODE, e.message.toString(), defaultData))
            }
        } else {
            Either.Left(Failure.NetworkConnection())
        }
    }
}