package com.orlinskas.calculator.network

import com.orlinskas.calculator.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideClient(authInterceptor: AuthInterceptor): OkHttpClient {

    return OkHttpClient.Builder().apply {
        readTimeout(10, TimeUnit.SECONDS)
        connectTimeout(10, TimeUnit.SECONDS)
    }.addInterceptor(authInterceptor).addNetworkInterceptor(HttpLoggingInterceptor()).build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
