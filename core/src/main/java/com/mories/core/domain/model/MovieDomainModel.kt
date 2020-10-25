package com.mories.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDomainModel(
    val id: Int? = null,
    val overview: String? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null,
    val popularity: Double? = null,
    val releaseDate: String? = null,
    val backdropPath: String? = null,
    val posterPath: String? = null,
    val title: String? = null,
    val originalTitle: String? = null,
    val originalLanguage: String? = null,
    val video: Boolean? = null,
    var isFavorite: Boolean = false
) : Parcelable