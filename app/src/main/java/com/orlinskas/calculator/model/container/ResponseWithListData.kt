package com.orlinskas.calculator.model.container

data class ResponseWithListData<T>(val code: Int, val message: String, val data: List<T>)
