package com.mertyazi.moviesappxml.core.data.usecase

import com.mertyazi.moviesappxml.core.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsMovieInFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend fun execute(movieId: String): Boolean =
        favoritesRepository.isFavorite(movieId)
}