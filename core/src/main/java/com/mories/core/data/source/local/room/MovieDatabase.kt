package com.mories.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mories.core.data.source.local.entity.MovieItemsEntity

@Database(entities = [MovieItemsEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}