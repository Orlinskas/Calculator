package com.orlinskas.calculator.model.container

data class ResponseWithMapData<T>(val code: Int, val message: String, val data: Map<String, T>)