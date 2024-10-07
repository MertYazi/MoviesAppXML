package com.mertyazi.moviesappxml.movies.data.repository

import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.movies.data.api.MoviesApi
import com.mertyazi.moviesappxml.movies.data.mapper.toMovies
import com.mertyazi.moviesappxml.movies.domain.repository.MoviesRepository
import com.mertyazi.moviesappxml.movies.domain.model.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Mert Yazi
 */

class MoviesRepositoryImp @Inject constructor(
    private val moviesApi: MoviesApi
): MoviesRepository {

    override suspend fun getPopularMovies(): Flow<Resource<Movies>> {
        return flow {
            emit(Resource.Loading())
            val result = moviesApi.getPopularMovies().toMovies()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun getUpcomingMovies(): Flow<Resource<Movies>> {
        return flow {
            emit(Resource.Loading())
            val result = moviesApi.getUpcomingMovies().toMovies()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
}