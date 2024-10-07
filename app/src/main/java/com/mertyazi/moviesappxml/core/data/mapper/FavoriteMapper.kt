package com.mertyazi.moviesappxml.core.data.mapper

import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie

/**
 * Created by Mert Yazi
 */

fun Details.toFavoriteMovie(): FavoriteMovie {
    return FavoriteMovie(
        id = id.toString(),
        movieTitle = title,
        moviePoster = backdropPath,
        moviePosterWithTitle = posterPath,
    )
}