package com.orlinskas.calculator.presentation.result

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.orlinskas.calculator.SaveStatus
import com.orlinskas.calculator.data.model.CalculatorResultModel
import com.orlinskas.calculator.interactor.SaveCalculatorResultUseCase
import ua.brander.core.viewmodel.BaseViewModel

class ResultViewModel(val context: Context, val saveCalculatorResultUseCase: SaveCalculatorResultUseCase) : BaseViewModel() {
    val result: MutableLiveData<CalculatorResultModel> = MutableLiveData()
    val savingStatus: MutableLiveData<SaveStatus> = MutableLiveData()

    init {
        savingStatus.value = SaveStatus.WAIT
    }

    fun saveResult(calculatorResultModel: CalculatorResultModel) {
        savingStatus.value = SaveStatus.IN_PROGRESS

        saveCalculatorResultUseCase(calculatorResultModel){
            if(it.isRight) {
                savingStatus.value = SaveStatus.DONE
            } else {
                savingStatus.value = SaveStatus.FAIL
            }
        }
    }

}