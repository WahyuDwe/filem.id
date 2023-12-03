package com.dwi.filmid.core.domain.usecase

import com.dwi.filmid.core.data.source.MoviesRepository
import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val moviesRepository: MoviesRepository) : MoviesUseCase {
    override fun getAllNowPlayingMovies(): Flow<Resource<List<Movies>>> =
        moviesRepository.getAllNowPlayingMovies()

    override fun getAllPopularMovies(): Flow<Resource<List<Movies>>> =
        moviesRepository.getAllPopularMovies()

    override fun getFavoriteMovies(): Flow<List<Movies>> = moviesRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movies: Movies, state: Boolean) =
        moviesRepository.setFavoriteMovies(movies, state)
}