package com.orlinskas.calculator.repository

import com.orlinskas.calculator.data.AppDatabase
import com.orlinskas.calculator.data.dao.CalculatorResultDao
import com.orlinskas.calculator.getDefaultSceleton
import com.orlinskas.calculator.network.request.CalculatorRequest
import com.orlinskas.calculator.network.response.CalculatorResponse
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.network.Api
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.platform.NetworkHandler

class CalculatorRepository(
    private val api: Api,
    private val networkHandler: NetworkHandler,
    private val appDatabase: AppDatabase

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

    fun save(params: CalculatorResultModel): Int {
        return appDatabase.calculatorResultDao().saveCalculatorResult(params)
    }
}