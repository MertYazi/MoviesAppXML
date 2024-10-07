package com.mertyazi.moviesappxml.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.core.data.usecase.AddOrRemoveFromFavoritesUseCase
import com.mertyazi.moviesappxml.core.data.usecase.IsMovieInFavoritesUseCase
import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.details.domain.repository.DetailsRepository
import com.mertyazi.moviesappxml.details.presentation.state.DetailsState
import com.mertyazi.moviesappxml.details.presentation.state.VideosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mert Yazi
 */

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val isMovieInFavoritesUseCase: IsMovieInFavoritesUseCase,
    private val addOrRemoveFromFavoritesUseCase: AddOrRemoveFromFavoritesUseCase,
    private val detailsRepository: DetailsRepository
): ViewModel() {

    private val _detailsState = MutableStateFlow(DetailsState())
    val detailsState = _detailsState.asStateFlow()

    private val _videosState = MutableStateFlow(VideosState())
    val videosState = _videosState.asStateFlow()

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            detailsRepository.getMovieDetails(movieId).collect { result ->
                when(result) {
                    is Resource.Error -> {
                        _detailsState.update {
                            it.copy(loading = false)
                        }
                    }

                    is Resource.Success -> {
                        _detailsState.update {
                            result.data?.let {
                                result.data.isFavourite = isMovieInFavoritesUseCase.execute(
                                    result.data.id.toString()
                                )
                            }
                            it.copy(
                                details = result.data!!,
                                loading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _detailsState.update {
                            it.copy(loading = true)
                        }
                    }
                }
            }
        }
    }

    fun getMovieVideos(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            detailsRepository.getMovieVideos(movieId).collect { result ->
                when(result) {
                    is Resource.Error -> {
                        _videosState.update {
                            it.copy(loading = false)
                        }
                    }

                    is Resource.Success -> {
                        _videosState.update {
                            it.copy(
                                videos = result.data!!,
                                loading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _videosState.update {
                            it.copy(loading = true)
                        }
                    }
                }
            }
        }
    }

    fun favoriteIconClicked(movie: Details) {
        viewModelScope.launch(Dispatchers.IO) {
            addOrRemoveFromFavoritesUseCase.execute(movie)
            val currentViewState = _detailsState.value
            (currentViewState as? DetailsState)?.let { content ->
                _detailsState.update {
                    content.copy(
                        details = content.details.copy(
                            isFavourite = isMovieInFavoritesUseCase.execute(movie.id.toString())
                        )
                    )
                }
            }
        }
    }
}