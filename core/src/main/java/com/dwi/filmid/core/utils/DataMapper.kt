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
        isFavorite = input.isFavorite
    )

}