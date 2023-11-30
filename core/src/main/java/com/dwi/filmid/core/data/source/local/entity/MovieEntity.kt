package com.dwi.filmid.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    val id: Int,

    @ColumnInfo(name = "title_movie")
    val title: String,

    @ColumnInfo(name = "overview_movie")
    val overview: String,

    @ColumnInfo(name = "poster_path_movie")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path_movie")
    val backdropPath: String,

    @ColumnInfo(name = "release_date_movie")
    val releaseDate: String,

    @ColumnInfo(name = "is_favorite_movie")
    val isFavorite: Boolean = false
)
