package com.dwi.filmid.core.data.source

import com.dwi.filmid.core.data.source.local.LocalDataSource
import com.dwi.filmid.core.data.source.remote.RemoteDataSource
import com.dwi.filmid.core.data.source.remote.network.ApiResponse
import com.dwi.filmid.core.data.source.remote.response.MovieResponse
import com.dwi.filmid.core.domain.model.Movies
import com.dwi.filmid.core.domain.repository.IMoviesRepository
import com.dwi.filmid.core.utils.AppExecutors
import com.dwi.filmid.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMoviesRepository {
    override fun getAllNowPlayingMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDb(): Flow<List<Movies>> =
                localDataSource.getNowPlayingMovies().map { DataMapper.mapEntitiesToDomain(it) }


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllNowPlayingMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }

            override fun shouldFetch(data: List<Movies>?): Boolean = data.isNullOrEmpty()

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}