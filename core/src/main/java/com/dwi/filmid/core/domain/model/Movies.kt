package com.dwi.filmid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val idMovie: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val rating: Double,
    var isFavorite: Boolean
) : Parcelable
