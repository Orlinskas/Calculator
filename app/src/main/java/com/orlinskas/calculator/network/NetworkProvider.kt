package com.orlinskas.calculator.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.orlinskas.calculator.BASE_URL
import com.orlinskas.calculator.di.provideStetho
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
}

fun provideClient(authInterceptor: AuthInterceptor): OkHttpClient {

    return OkHttpClient.Builder().apply {
        readTimeout(10, TimeUnit.SECONDS)
        connectTimeout(10, TimeUnit.SECONDS)
    }.apply {
        addInterceptor(authInterceptor)
        addNetworkInterceptor(HttpLoggingInterceptor())
        addNetworkInterceptor(StethoInterceptor())
        addInterceptor(OkHttpProfilerInterceptor())
    }.build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
