package com.dwi.filmid.core.utils

import com.dwi.filmid.core.data.source.local.entity.MovieEntity
import com.dwi.filmid.core.data.source.remote.response.MovieResponse
import com.dwi.filmid.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>, movieType: String): List<MovieEntity> {
        val moviesList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                idMovie = it.id,
                title = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                movieType = movieType,
                movieRating = it.voteAverage,
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> = input.map {
        Movies(
            idMovie = it.idMovie,
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            rating = it.movieRating,
            isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: Movies): MovieEntity = MovieEntity(
        idMovie = input.idMovie,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        movieType = null,
        movieRating = input.rating,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: MovieEntity): Movies = Movies(
        idMovie = input.idMovie,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        rating = input.movieRating,
        isFavorite = input.isFavorite
    )

    fun mapResponseToEntity(input: MovieResponse): MovieEntity = MovieEntity(
        idMovie = input.id,
        title = input.originalTitle,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        movieType = null,
        movieRating = input.voteAverage,
        isFavorite = false
    )

}