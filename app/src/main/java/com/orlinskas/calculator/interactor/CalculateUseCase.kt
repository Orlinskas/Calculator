package com.orlinskas.calculator.interactor

import com.orlinskas.calculator.network.request.CalculatorRequest
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.repository.CalculatorRepository
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.interactor.UseCase

class CalculateUseCase(private val calculatorRepository: CalculatorRepository) :
    UseCase<CalculatorResultModel, CalculatorRequest>() {

    override suspend fun run(params: CalculatorRequest): Either<Failure, CalculatorResultModel> {
        return calculatorRepository.calculate(params)
    }
}