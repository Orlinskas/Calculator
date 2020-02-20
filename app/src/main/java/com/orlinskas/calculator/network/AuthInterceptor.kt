package com.orlinskas.calculator.network

import com.orlinskas.calculator.HEADER_ACCEPT
import com.orlinskas.calculator.HEADER_TYPE
import com.orlinskas.calculator.HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val requestBuilder = req.newBuilder().apply {
            addHeader(HEADER_TYPE, HEADER_VALUE)
            addHeader(HEADER_ACCEPT, HEADER_VALUE)
        }
        req = requestBuilder.build()
        return chain.proceed(req)
    }
}