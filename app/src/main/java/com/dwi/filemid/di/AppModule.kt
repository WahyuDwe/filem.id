package com.dwi.filemid.di

import com.dwi.filemid.detail.DetailViewModel
import com.dwi.filemid.home.HomeViewModel
import com.dwi.filmid.core.domain.usecase.MoviesInteractor
import com.dwi.filmid.core.domain.usecase.MoviesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}