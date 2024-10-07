package com.mertyazi.moviesappxml.movies.presentation.state

import com.mertyazi.moviesappxml.movies.domain.model.Movies

/**
 * Created by Mert Yazi
 */

data class UpcomingState(
    val upcoming: Movies = Movies(),
    val loading: Boolean = false,
    val error: String = ""
)
