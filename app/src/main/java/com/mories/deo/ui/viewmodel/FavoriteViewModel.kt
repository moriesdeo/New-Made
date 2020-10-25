package com.mories.deo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mories.core.domain.model.MovieDomainModel
import com.mories.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {

    val movie = moviesUseCase.getFavoriteMovie().asLiveData()

    internal fun setFavorite(movieDomainModel: MovieDomainModel, isFavorite: Boolean) {
        moviesUseCase.setFavoriteMovie(movieDomainModel, isFavorite)
    }
}