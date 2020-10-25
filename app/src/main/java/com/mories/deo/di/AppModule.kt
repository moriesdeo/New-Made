package com.mories.deo.di

import com.mories.core.domain.MovieInteractor
import com.mories.core.domain.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MoviesUseCase

}