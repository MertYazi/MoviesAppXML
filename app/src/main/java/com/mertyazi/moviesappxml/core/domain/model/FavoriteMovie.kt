package com.mertyazi.moviesappxml.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mert Yazi
 */

@Entity
class FavoriteMovie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "movie_title") val movieTitle : String,
    @ColumnInfo(name = "movie_poster") val moviePoster : String,
    @ColumnInfo(name = "movie_poster_with_title") val moviePosterWithTitle : String
)
