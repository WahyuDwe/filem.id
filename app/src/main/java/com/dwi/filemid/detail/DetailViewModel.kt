package com.dwi.filemid.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun getDetailMovie(movieId: Int) = moviesUseCase.getDetailMovie(movieId).asLiveData()

    fun setFavoriteMovie(movies: Movies, newState: Boolean) = moviesUseCase.setFavoriteMovies(movies, newState)
}