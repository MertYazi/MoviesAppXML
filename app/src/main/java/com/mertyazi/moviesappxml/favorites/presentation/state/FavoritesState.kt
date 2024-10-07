package com.mertyazi.moviesappxml.favorites.presentation.state

import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie

/**
 * Created by Mert Yazi
 */

data class FavoritesState(
    val favorites: List<FavoriteMovie> = listOf(),
    val loading: Boolean = false,
    val error: String = ""
)
