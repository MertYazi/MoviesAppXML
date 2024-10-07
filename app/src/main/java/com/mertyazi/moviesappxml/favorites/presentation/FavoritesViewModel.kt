package com.mertyazi.moviesappxml.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyazi.moviesappxml.core.domain.repository.FavoritesRepository
import com.mertyazi.moviesappxml.core.util.Resource
import com.mertyazi.moviesappxml.favorites.presentation.state.FavoritesState
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
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
): ViewModel() {

    private val _favoritesState = MutableStateFlow(FavoritesState())
    val favoritesState = _favoritesState.asStateFlow()

    fun getFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.getFavorites().collect { result ->
                when(result) {
                    is Resource.Error -> {
                        _favoritesState.update {
                            it.copy(loading = false)
                        }
                    }

                    is Resource.Success -> {
                        _favoritesState.update {
                            it.copy(
                                favorites = result.data!!,
                                loading = false
                            )
                        }

                    }

                    is Resource.Loading -> {
                        _favoritesState.update {
                            it.copy(loading = true)
                        }
                    }
                }
            }
        }
    }
}