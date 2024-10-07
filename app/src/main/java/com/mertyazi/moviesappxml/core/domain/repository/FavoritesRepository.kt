package com.mertyazi.moviesappxml.core.domain.repository

import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mert Yazi
 */

interface FavoritesRepository {

    suspend fun isFavorite(movieId : String) : Boolean

    suspend fun getFavorites(): Flow<Resource<List<FavoriteMovie>>>

    suspend fun addToFavorites(movie : FavoriteMovie)

    suspend fun removeFromFavorites(movie : FavoriteMovie)
}