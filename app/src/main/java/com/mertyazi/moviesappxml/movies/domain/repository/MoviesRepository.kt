package com.mertyazi.moviesappxml.movies.domain.repository

import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.movies.domain.model.Movies
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mert Yazi
 */

interface MoviesRepository {

    suspend fun getPopularMovies(): Flow<Resource<Movies>>
    suspend fun getUpcomingMovies(): Flow<Resource<Movies>>
}