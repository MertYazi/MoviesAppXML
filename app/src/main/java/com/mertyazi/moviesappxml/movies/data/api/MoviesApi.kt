package com.mertyazi.moviesappxml.movies.data.api

import com.mertyazi.moviesappxml.core.util.Constants.API_KEY
import com.mertyazi.moviesappxml.core.util.Constants.API_KEY_QUERY
import com.mertyazi.moviesappxml.core.util.Constants.PAGE_QUERY
import com.mertyazi.moviesappxml.movies.data.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mert Yazi
 */

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(PAGE_QUERY) page: Int = 1,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): MoviesDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query(PAGE_QUERY) page: Int = 1,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): MoviesDto
}