package com.mories.core.domain.usecase

import com.mories.core.data.Resource
import com.mories.core.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getMovieRemoter(): Flow<Resource<List<MovieDomainModel>>>
    fun getFavoriteMovie(): Flow<List<MovieDomainModel>>
    fun setFavoriteMovie(movieItems: MovieDomainModel, state: Boolean)
}