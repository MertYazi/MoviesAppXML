package com.mertyazi.moviesappxml.movies.data.mapper

import com.mertyazi.moviesappxml.movies.data.dto.MoviesDto
import com.mertyazi.moviesappxml.movies.domain.model.Movies
import com.mertyazi.moviesappxml.movies.domain.model.Result

/**
 * Created by Mert Yazi
 */

fun MoviesDto.toMovies(): Movies {
    return Movies(
        page = page,
        results = results.map {
            Result(
                adult = it.adult,
                backdropPath = it.backdrop_path,
                genreIds = it.genre_ids,
                id = it.id,
                originalLanguage = it.original_language,
                originalTitle = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.poster_path,
                releaseDate = it.release_date,
                title = it.title,
                video = it.video,
                voteAverage = it.vote_average,
                voteCount = it.vote_count
        ) },
        totalPages = total_pages,
        totalResults = total_results
    )
}
