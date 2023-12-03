package com.dwi.filmid.core.data.source.local

import com.dwi.filmid.core.data.source.local.entity.MovieEntity
import com.dwi.filmid.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getNowPlayingMovies(): Flow<List<MovieEntity>> = movieDao.getNowPlayingMovies()

    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    fun getDetailMovie(movieId: Int): Flow<MovieEntity> = movieDao.getDetailMovie(movieId)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    suspend fun insertDetailMovie(movie: MovieEntity) = movieDao.insertDetailMovie(movie)

    fun setFavoriteMovie(movies: MovieEntity, newState: Boolean) {
        movies.isFavorite = newState
        movieDao.updateFavoriteMovies(movies)
    }

}