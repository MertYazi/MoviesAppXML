package com.mertyazi.moviesappxml.core.data.repository

import com.mertyazi.moviesappxml.core.data.db.FavoritesDao
import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie
import com.mertyazi.moviesappxml.core.domain.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mert Yazi
 */

class FavoritesRepositoryImp @Inject constructor(
    private val favoritesDao: FavoritesDao
): FavoritesRepository {
    override suspend fun isFavorite(movieId: String): Boolean {
        return withContext(Dispatchers.IO) {
            favoritesDao.isMovieFavorite(movieId) != null
        }
    }

    override suspend fun getFavorites(): Flow<Resource<List<FavoriteMovie>>> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(Resource.Loading())
                val result = favoritesDao.getFavoriteMovies()
                emit(Resource.Success(result))
            }.catch {
                emit(Resource.Error(it.message.toString()))
            }
        }
    }

    override suspend fun addToFavorites(movie: FavoriteMovie) {
        withContext(Dispatchers.IO) {
            favoritesDao.addMovieToFavorites(
                movie
            )
        }
    }

    override suspend fun removeFromFavorites(movie: FavoriteMovie) {
        withContext(Dispatchers.IO) {
            favoritesDao.removeMovieFromFavorites(
                movie
            )
        }
    }

}