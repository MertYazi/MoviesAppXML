package com.mertyazi.moviesappxml.details.data.repository

import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.details.data.api.DetailsApi
import com.mertyazi.moviesappxml.details.data.mapper.toDetails
import com.mertyazi.moviesappxml.details.data.mapper.toVideos
import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.details.domain.model.Videos
import com.mertyazi.moviesappxml.details.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Mert Yazi
 */

class DetailsRepositoryImp @Inject constructor(
    private val detailsApi: DetailsApi
): DetailsRepository {

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<Details>> {
        return flow {
            emit(Resource.Loading())
            val result = detailsApi.getMovieDetails(movieId).toDetails()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun getMovieVideos(movieId: String): Flow<Resource<Videos>> {
        return flow {
            emit(Resource.Loading())
            val result = detailsApi.getMovieVideos(movieId).toVideos()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
}