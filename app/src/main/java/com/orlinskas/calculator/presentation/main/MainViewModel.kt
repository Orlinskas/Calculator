package com.orlinskas.calculator.presentation.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.orlinskas.calculator.Isolation
import com.orlinskas.calculator.R
import com.orlinskas.calculator.interactor.SimpleCalculatorUseCase
import com.orlinskas.calculator.model.CalculatorRequest
import com.orlinskas.calculator.model.CalculatorResultModel
import ua.brander.core.viewmodel.BaseViewModel

class MainViewModel(val context: Context, private val simpleCalculatorUseCase: SimpleCalculatorUseCase) : BaseViewModel() {
    val OPTIMAL = context.resources.getString(R.string.optimal)
    val ECONOM = context.resources.getString(R.string.econom)
    val REGULATION_YES = context.resources.getString(R.string.regulation_yes)
    val REGULATION_NO = context.resources.getString(R.string.regilation_no)

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

    private fun processCalculatorData(calculatorResult: CalculatorResultModel) {
        result.value = calculatorResult
    }

    fun getIsolation(item: String): String {
        return when(item) {
            OPTIMAL -> Isolation.OPT.value
            ECONOM -> Isolation.ECO.value
            else -> Isolation.ECO.value
        }
    }

    fun getRegulation(item: String): Boolean {
        return when(item) {
            REGULATION_YES -> true
            REGULATION_NO -> false
            else -> false
        }
    }
}