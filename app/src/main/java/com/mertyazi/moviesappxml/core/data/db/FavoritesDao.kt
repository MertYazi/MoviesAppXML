package com.mertyazi.moviesappxml.core.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie

/**
 * Created by Mert Yazi
 */

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favoritemovie WHERE id=:id")
    fun isMovieFavorite(id: String): FavoriteMovie?

    @Query("SELECT * FROM favoritemovie")
    fun getFavoriteMovies(): List<FavoriteMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieToFavorites(movie: FavoriteMovie)

    @Delete
    fun removeMovieFromFavorites(movie: FavoriteMovie)
}