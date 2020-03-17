package com.orlinskas.calculator.interactor

import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.repository.CalculatorRepository
import ua.brander.core.exception.Failure
import ua.brander.core.functional.Either
import ua.brander.core.interactor.UseCase

class GetSavedResultsUseCase(private val calculatorRepository: CalculatorRepository) :
    UseCase<List<CalculatorResultModel>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<CalculatorResultModel>> {
        return Either.Right(calculatorRepository.getAllSavedResults())
    }

}