package com.orlinskas.calculator.di

import com.orlinskas.calculator.interactor.SimpleCalculatorUseCase
import com.orlinskas.calculator.presentation.main.MainViewModel
import com.orlinskas.calculator.repository.SimpleCalculatorRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.brander.core.platform.NetworkHandler
import ua.brander.meetingroom.data.network.AuthInterceptor
import ua.brander.meetingroom.data.network.provideApi
import ua.brander.meetingroom.data.network.provideClient
import ua.brander.meetingroom.data.network.provideRetrofit
import ua.brander.meetingroom.presentation.splash.SplashViewModel

val viewModelModule = module {
    viewModel { MainViewModel(androidContext()) }
    viewModel { SplashViewModel() }
}
val netWorkModule = module {
    single { NetworkHandler(androidContext()) }

    factory { AuthInterceptor() }
    factory { provideClient(get()) }
    factory { provideRetrofit(get()) }
    factory { provideApi(get()) }
}
val reposiryModule = module {
    single { SimpleCalculatorRepository(get(), get()) }
}
val useCaseModule = module {
    factory{ SimpleCalculatorUseCase(get()) }
}