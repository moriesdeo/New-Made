package com.mories.core.di

import android.content.Context
import androidx.room.Room
import com.mories.core.data.source.local.room.MovieDao
import com.mories.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "Movie.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: MovieDatabase): MovieDao = database.movieDao()
}