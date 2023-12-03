package com.dwi.filmid.core.domain.usecase

import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllNowPlayingMovies(): Flow<Resource<List<Movies>>>
    fun getAllPopularMovies(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies, state: Boolean)
}