package com.example.myapplication.di

import com.example.data.mapper.LoginDataMapper
import com.example.data.repository.MainRepositoryImpl
import com.example.datasource.service.RetrofitFactory
import com.example.datasource.source.RemoteDataSource
import com.example.domain.interactor.LoginUseCase
import com.example.domain.repository.MainRepository
import com.example.myapplication.mapper.LoginUiMapper
import com.example.myapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    single { LoginUiMapper() }
    single { LoginUseCase(get()) }
    factory { RetrofitFactory.create() }
}

val dataModule = module {
    factory { LoginDataMapper() }
    factory<MainRepository> { MainRepositoryImpl(get(), get()) }
}
val dataSourceModule = module {
    factory { RemoteDataSource(get()) }
}