package com.orlinskas.calculator.presentation.main

import android.content.Context
import com.orlinskas.calculator.interactor.SimpleCalculatorUseCase
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import ua.brander.core.viewmodel.BaseViewModel

class MainViewModel(val context: Context, private val simpleCalculatorUseCase: SimpleCalculatorUseCase) : BaseViewModel() {

    fun checkValidDistance(distanse: Any?): Boolean {
        return distanse != null && distanse is Int && distanse < 999 && distanse > 0
    }

    fun <T> checkValidArray(element: Any?, array: Array<T>): Boolean {
        return  array.contains(element)
    }

    fun calculate(params: CalculatorRequest) {
        simpleCalculatorUseCase(params) {
            it.either(::handleFailure, ::processCalculatorData)
        }
    }

    private fun processCalculatorData(resultList: List<CalculatorResultModel>) {
        val result = resultList.get(0)
        val value = result.total_sum
    }
}