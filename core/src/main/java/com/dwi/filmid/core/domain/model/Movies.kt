package com.dwi.filmid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val idMovie: Int,
    val title: String,
    val overview: String,
    val posterPath: String? = null,
    val releaseDate: String,
    val rating: Double,
    val movieType: String? = null,
    var isFavorite: Boolean
) : Parcelable
