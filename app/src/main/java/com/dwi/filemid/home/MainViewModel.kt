package com.dwi.filemid.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dwi.filmid.core.domain.usecase.MoviesUseCase

class MainViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val nowPlayingMovies = moviesUseCase.getAllNowPlayingMovies().asLiveData()

    val popularMovies = moviesUseCase.getAllPopularMovies().asLiveData()
}