package com.dwi.filmid.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    val idMovie: Int,

    @ColumnInfo(name = "title_movie")
    val title: String,

    @ColumnInfo(name = "overview_movie")
    val overview: String,

    @ColumnInfo(name = "poster_path_movie")
    val posterPath: String,

    @ColumnInfo(name = "release_date_movie")
    val releaseDate: String,

    @ColumnInfo(name = "movie_type")
    val movieType: String?,

    @ColumnInfo(name = "is_favorite_movie")
    var isFavorite: Boolean = false
) : Parcelable
