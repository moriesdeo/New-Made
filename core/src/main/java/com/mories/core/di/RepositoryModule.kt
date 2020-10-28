package com.mories.core.di

import com.mories.core.data.MovieRepository
import com.mories.core.domain.IMovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(iMovieRepository: MovieRepository): IMovieRepository

}