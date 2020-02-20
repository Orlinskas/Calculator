package com.orlinskas.calculator.presentation.result

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.orlinskas.calculator.model.CalculatorResultModel
import ua.brander.core.viewmodel.BaseViewModel

class ResultViewModel(val context: Context) : BaseViewModel() {
    val result: MutableLiveData<CalculatorResultModel> = MutableLiveData()

}