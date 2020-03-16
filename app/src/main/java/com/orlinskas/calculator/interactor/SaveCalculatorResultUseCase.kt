package com.orlinskas.calculator.interactor

import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.repository.CalculatorRepository
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.interactor.UseCase

class SaveCalculatorResultUseCase(private val calculatorRepository: CalculatorRepository)
    : UseCase<Long, CalculatorResultModel>() {

    override suspend fun run(params: CalculatorResultModel): Either<Failure, Long> {
       return Either.Right(calculatorRepository.save(params))
    }
}