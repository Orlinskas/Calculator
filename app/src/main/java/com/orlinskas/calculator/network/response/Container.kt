package com.orlinskas.calculator.network.response

data class ResponseData<T>(val data: T)

data class ResponseWithListData<T>(val data: List<T>)

data class ResponseWithMapData<T>(val data: Map<String, T>)