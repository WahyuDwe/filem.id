package com.dwi.filmid.core.data.source.remote.network

import com.dwi.filmid.core.BuildConfig
import com.dwi.filmid.core.data.source.remote.response.ListMovieResponse
import com.dwi.filmid.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") token: String = BuildConfig.AUTHORIZATION,
        @Query("region") region: String = "id",
    ): ListMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") token: String = BuildConfig.AUTHORIZATION,
    ): ListMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Header("Authorization") token: String = BuildConfig.AUTHORIZATION,
        @Path("movie_id") movieId: Int,
    ): MovieResponse

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Header("Authorization") token: String = BuildConfig.AUTHORIZATION,
        @Query("query") query: String,
    ): MovieResponse

}