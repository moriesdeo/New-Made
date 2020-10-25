package com.mories.core.domain

import com.mories.core.data.Resource
import com.mories.core.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<MovieDomainModel>>>
    fun getFavoriteMovie(): Flow<List<MovieDomainModel>>
    fun setFavoriteMovie(movieDomainModel: MovieDomainModel, state: Boolean)
}