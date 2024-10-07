package com.mertyazi.moviesappxml.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.movies.domain.repository.MoviesRepository
import com.mertyazi.moviesappxml.movies.presentation.state.PopularState
import com.mertyazi.moviesappxml.movies.presentation.state.UpcomingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mert Yazi
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    private val _upcomingMoviesState = MutableStateFlow(UpcomingState())
    val upcomingMoviesState = _upcomingMoviesState.asStateFlow()

    private val _popularMoviesState = MutableStateFlow(PopularState())
    val popularMoviesState = _popularMoviesState.asStateFlow()

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            moviesRepository.getUpcomingMovies().collect { result ->
                when(result) {
                    is Resource.Error -> {
                        _upcomingMoviesState.update {
                            it.copy(loading = false)
                        }
                    }

                    is Resource.Success -> {
                        _upcomingMoviesState.update {
                            it.copy(
                                upcoming = result.data!!,
                                loading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _upcomingMoviesState.update {
                            it.copy(loading = true)
                        }
                    }
                }
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            moviesRepository.getPopularMovies().collect { result ->
                when(result) {
                    is Resource.Error -> {
                        _popularMoviesState.update {
                            it.copy(loading = false)
                        }
                    }

                    is Resource.Success -> {
                        _popularMoviesState.update {
                            it.copy(
                                popular = result.data!!,
                                loading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _popularMoviesState.update {
                            it.copy(loading = true)
                        }
                    }
                }
            }
        }
    }
}