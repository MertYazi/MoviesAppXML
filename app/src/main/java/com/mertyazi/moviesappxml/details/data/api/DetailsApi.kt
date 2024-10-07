package com.mertyazi.moviesappxml.details.data.api

import com.mertyazi.moviesappxml.core.util.Constants.API_KEY
import com.mertyazi.moviesappxml.core.util.Constants.API_KEY_QUERY
import com.mertyazi.moviesappxml.core.util.Constants.MOVIE_ID_PATH
import com.mertyazi.moviesappxml.details.data.dto.DetailsDto
import com.mertyazi.moviesappxml.details.data.dto.VideosDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mert Yazi
 */

interface DetailsApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path(MOVIE_ID_PATH) movieId: String = "",
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): DetailsDto

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path(MOVIE_ID_PATH) movieId: String = "",
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): VideosDto

}