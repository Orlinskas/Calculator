package com.orlinskas.calculator.presentation.splash

import androidx.lifecycle.MutableLiveData
import ua.brander.core.viewmodel.BaseViewModel

class SplashViewModel() : BaseViewModel() { // do use case ander Any
    val isTokenExist = MutableLiveData<Boolean>()

    fun isExist() {
        //CheckTokenExistUseCase(UseCase.None()) { it.either(::handleFailure, ::tokenSessionExist) }
    }

    private fun tokenSessionExist(exist: Boolean) {
        isTokenExist.value = exist
    }
}