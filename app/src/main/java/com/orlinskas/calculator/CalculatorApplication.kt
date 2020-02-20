package com.orlinskas.calculator

import android.app.Application
import com.orlinskas.calculator.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CalculatorApplication)
            modules(listOf(viewModelModule, netWorkModule, reposiryModule, useCaseModule, adaptersModule))
        }
    }
}