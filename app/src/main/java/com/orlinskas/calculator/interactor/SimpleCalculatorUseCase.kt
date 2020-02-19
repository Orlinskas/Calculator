package com.orlinskas.calculator.interactor

import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import com.orlinskas.calculator.repository.SimpleCalculatorRepository
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.interactor.UseCase

class SimpleCalculatorUseCase(private val simpleCalculatorRepository: SimpleCalculatorRepository) :
    UseCase<List<CalculatorResultModel>, CalculatorRequest>() {

    override suspend fun run(params: CalculatorRequest): Either<Failure, List<CalculatorResultModel>> {
        return simpleCalculatorRepository.calculate(params)
    }

}