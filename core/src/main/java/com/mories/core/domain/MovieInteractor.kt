package com.mories.core.domain

import com.mories.core.data.Resource
import com.mories.core.domain.model.MovieDomainModel
import com.mories.core.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val iMovieRepository: IMovieRepository) :
    MoviesUseCase {

    override fun getMovieRemoter(): Flow<Resource<List<MovieDomainModel>>> {
        return iMovieRepository.getAllMovie()
    }

    override fun getFavoriteMovie(): Flow<List<MovieDomainModel>> {
        return iMovieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movieItems: MovieDomainModel, state: Boolean) {
        iMovieRepository.setFavoriteMovie(movieItems, state)
    }

}