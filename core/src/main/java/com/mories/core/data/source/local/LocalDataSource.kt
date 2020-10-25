package com.mories.core.data.source.local

import com.mories.core.data.source.local.entity.MovieItemsEntity
import com.mories.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllTourism(): Flow<List<MovieItemsEntity>> = movieDao.getAllMovieFavorite()

    fun getFavoriteTourism(): Flow<List<MovieItemsEntity>> = movieDao.getAllMovieFavorite()

    suspend fun insertTourism(movieList: List<MovieItemsEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movieItems: MovieItemsEntity) {
        movieDao.updateFavoriteMovie(movieItems)
    }
}