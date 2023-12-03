package com.dwi.filmid.core.domain.repository

import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getAllNowPlayingMovies(): Flow<Resource<List<Movies>>>
    fun getAllPopularMovies(): Flow<Resource<List<Movies>>>
    fun getDetailMovie(movieId: Int): Flow<Resource<Movies>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies, state: Boolean)
}