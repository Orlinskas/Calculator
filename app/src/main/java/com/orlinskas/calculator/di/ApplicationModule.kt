package com.orlinskas.calculator.di

import com.orlinskas.calculator.adapter.ProductAdapter
import com.orlinskas.calculator.interactor.SimpleCalculatorUseCase
import com.orlinskas.calculator.network.AuthInterceptor
import com.orlinskas.calculator.network.provideApi
import com.orlinskas.calculator.network.provideClient
import com.orlinskas.calculator.network.provideRetrofit
import com.orlinskas.calculator.presentation.main.MainViewModel
import com.orlinskas.calculator.presentation.result.ResultViewModel
import com.orlinskas.calculator.presentation.splash.SplashViewModel
import com.orlinskas.calculator.repository.SimpleCalculatorRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.brander.core.platform.NetworkHandler

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel(androidContext(), get()) }
    viewModel { ResultViewModel(androidContext()) }
}
val netWorkModule = module {
    single { NetworkHandler(androidContext()) }
    factory { AuthInterceptor() }
    factory { provideClient(get()) }
    factory { provideRetrofit(get()) }
    factory { provideApi(get()) }
}
val repositoryModule = module {
    single { SimpleCalculatorRepository(get(), get()) }
}
val useCaseModule = module {
    factory{ SimpleCalculatorUseCase(get()) }
}
val adaptersModule = module {
    factory { ProductAdapter(androidContext()) }
}