package com.orlinskas.calculator.presentation.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.orlinskas.calculator.interactor.SimpleCalculatorUseCase
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import ua.brander.core.viewmodel.BaseViewModel

class MainViewModel(val context: Context, private val simpleCalculatorUseCase: SimpleCalculatorUseCase) : BaseViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val result: MutableLiveData<CalculatorResultModel> = MutableLiveData()

    fun checkValidDistance(distanse: String): Boolean {
        return try {
            val int = distanse.toInt()
            int in 1..999
        } catch (e: Exception) {
            false
        }
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
        result.value = resultList[0]
    }
}