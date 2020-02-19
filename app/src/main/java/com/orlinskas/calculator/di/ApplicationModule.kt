package com.orlinskas.calculator.di

import com.orlinskas.calculator.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.brander.meetingroom.presentation.splash.SplashViewModel

val viewModelModule = module {
    viewModel { MainViewModel(androidContext()) }
    viewModel { SplashViewModel() }
}