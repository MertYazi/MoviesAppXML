package com.mertyazi.moviesappxml.details.domain.repository

import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.details.domain.model.Videos
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mert Yazi
 */

interface DetailsRepository {

    suspend fun getMovieDetails(movieId: String): Flow<Resource<Details>>
    suspend fun getMovieVideos(movieId: String): Flow<Resource<Videos>>
}