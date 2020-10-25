package com.mories.core.utils

import com.mories.core.data.source.local.entity.MovieItemsEntity
import com.mories.core.data.source.remote.response.ResponseMovie
import com.mories.core.domain.model.MovieDomainModel


object DataMapper {

    fun mapResponsesToEntities(input: List<ResponseMovie>): List<MovieItemsEntity> {
        val movie = ArrayList<MovieItemsEntity>()
        input.map {
            val movie2 = MovieItemsEntity(
                id = it.id,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteCount = it.voteCount,
                adult = it.adult,
                overview = it.overview,
                video = it.video,
                voteAverage = it.voteAverage
            )
            movie.add(movie2)
        }
        return movie
    }

    fun mapEntitiesToDomain(input: List<MovieItemsEntity>): List<MovieDomainModel> = input.map {
        MovieDomainModel(
            id = it.id,
            overview = it.overview,
            adult = it.adult,
            voteCount = it.voteCount,
            popularity = it.popularity,
            releaseDate = it.releaseDate,
            backdropPath = it.backdropPath,
            posterPath = it.posterPath,
            title = it.title,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            isFavorite = it.isFavorite ?: false
        )
    }

    fun mapDomainToEntity(input: MovieDomainModel, state: Boolean) = MovieItemsEntity(
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteAverage = 0.0,
        id = input.id,
        adult = input.adult,
        voteCount = input.voteCount,
        isFavorite = state
    )
}