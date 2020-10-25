package com.mories.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mories.core.domain.model.MovieDomainModel
import com.mories.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class Favorite2ViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {

    val movie = moviesUseCase.getFavoriteMovie().asLiveData()

    internal fun setFavorite(movieDomainModel: MovieDomainModel, isfavorite: Boolean) {
        moviesUseCase.setFavoriteMovie(movieDomainModel, isfavorite)
    }
}