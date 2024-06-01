package com.example.androidsubmision1.di

import com.example.androidsubmision1.ViewModel.FavoriteViewModel
import com.example.androidsubmission1.core.domain.usecase.FavoriteInteractor
import com.example.androidsubmission1.core.domain.usecase.FavoriteUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FavoriteUseCase>() { FavoriteInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
}