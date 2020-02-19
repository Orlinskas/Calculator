package com.orlinskas.calculator.presentation.main

import android.content.Context
import ua.brander.core.viewmodel.BaseViewModel

class MainViewModel(val context: Context) : BaseViewModel() {

    fun checkValidDistance(distanse: Any?): Boolean {
        return distanse != null && distanse is Int && distanse < 999 && distanse > 0
    }

    fun <T> checkValidArray(element: Any?, array: Array<T>): Boolean {
        return  array.contains(element)
    }
}