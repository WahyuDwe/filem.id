package com.dwi.filmid.core.domain.usecase

import com.dwi.filmid.core.data.source.Resource
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val moviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getAllNowPlayingMovies(): Flow<Resource<List<Movies>>> =
        moviesRepository.getAllNowPlayingMovies()

    override fun getAllPopularMovies(): Flow<Resource<List<Movies>>> =
        moviesRepository.getAllPopularMovies()

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movies>> =
        moviesRepository.getDetailMovie(movieId)

    override fun getSearchMovies(query: String): Flow<Resource<List<Movies>>> {
        return moviesRepository.getSearchMovies(query)
    }

    override fun getFavoriteMovies(): Flow<List<Movies>> = moviesRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movies: Movies, state: Boolean) =
        moviesRepository.setFavoriteMovies(movies, state)
}