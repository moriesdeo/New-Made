package com.mories.deo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mories.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movie = moviesUseCase.getMovieRemoter().asLiveData()
}