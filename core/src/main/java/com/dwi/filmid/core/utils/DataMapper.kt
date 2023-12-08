package com.dwi.filmid.core.utils

import com.dwi.filmid.core.data.source.local.entity.MovieEntity
import com.dwi.filmid.core.data.source.remote.response.MovieResponse
import com.dwi.filmid.core.domain.model.Movies

object DataMapper {

    // map from list response to entity
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

    // map from list entity to domain
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> = input.map {
        Movies(
            idMovie = it.idMovie,
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            rating = it.movieRating,
            movieType = it.movieType,
            isFavorite = it.isFavorite
        )
    }

    // map from domain to entity
    fun mapDomainToEntity(input: Movies): MovieEntity = MovieEntity(
        idMovie = input.idMovie,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        movieType = input.movieType,
        movieRating = input.rating,
        isFavorite = input.isFavorite
    )

    // map from entity to domain
    fun mapEntityToDomain(input: MovieEntity): Movies = Movies(
        idMovie = input.idMovie,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        rating = input.movieRating,
        movieType = input.movieType,
        isFavorite = input.isFavorite
    )

    // map from response detail movie to entity
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