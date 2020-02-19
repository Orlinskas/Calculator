package com.orlinskas.calculator.model.container

data class ResponseData<T>(val code:Int, val message:String, val data: T)