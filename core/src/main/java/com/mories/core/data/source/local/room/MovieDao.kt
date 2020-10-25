package com.mories.core.data.source.local.room

import androidx.room.*
import com.mories.core.data.source.local.entity.MovieItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovieFavorite(): Flow<List<MovieItemsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieItemsEntity>)

    @Update
    fun updateFavoriteMovie(movieItems: MovieItemsEntity)
}