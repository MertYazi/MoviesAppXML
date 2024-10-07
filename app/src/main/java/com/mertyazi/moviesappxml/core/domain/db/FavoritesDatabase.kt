package com.mertyazi.moviesappxml.core.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertyazi.moviesappxml.core.data.db.FavoritesDao
import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie

/**
 * Created by Mert Yazi
 */

@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase: RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}