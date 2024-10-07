package com.mertyazi.moviesappxml.core.data.usecase

import com.mertyazi.moviesappxml.core.data.mapper.toFavoriteMovie
import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.core.domain.repository.FavoritesRepository
import javax.inject.Inject

class AddOrRemoveFromFavoritesUseCase @Inject constructor(
    private val isMovieInFavoritesUseCase: IsMovieInFavoritesUseCase,
    private val favoritesRepository: FavoritesRepository
) {
    suspend fun execute(movie: Details) {
        if(isMovieInFavoritesUseCase.execute(movie.id.toString())){
            favoritesRepository.removeFromFavorites(
                movie.toFavoriteMovie()
            )
        } else {
            favoritesRepository.addToFavorites(
                movie.toFavoriteMovie()
            )
        }
    }
}