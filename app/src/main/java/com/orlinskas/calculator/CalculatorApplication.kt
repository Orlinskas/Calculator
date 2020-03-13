package com.orlinskas.calculator

import android.app.Application
import com.orlinskas.calculator.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CalculatorApplication)
            modules(listOf(viewModelModule, netWorkModule, repositoryModule, useCaseModule, adaptersModule))
        }

        if (BuildConfig.DEBUG) {
            provideStetho(this)
            Timber.plant(DebugTree())
        }

    }
}