package com.mertyazi.moviesappxml.details.data.mapper

import com.mertyazi.moviesappxml.details.data.dto.BelongsToCollectionDto
import com.mertyazi.moviesappxml.details.data.dto.DetailsDto
import com.mertyazi.moviesappxml.details.data.dto.GenreDto
import com.mertyazi.moviesappxml.details.domain.model.BelongsToCollection
import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.details.domain.model.Genre

/**
 * Created by Mert Yazi
 */

fun DetailsDto.toDetails(): Details {
    return Details(
        adult = adult,
        backdropPath = backdrop_path,
        belongsToCollection = belongs_to_collection?.toBelongsToCollection(),
        budget = budget,
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = id,
        imdbId = imdb_id,
        originCountry = origin_country,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        releaseDate = release_date,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = vote_average,
        voteCount = vote_count,
        isFavourite = false
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun BelongsToCollectionDto.toBelongsToCollection(): BelongsToCollection {
    return BelongsToCollection(
        backdropPath = backdrop_path,
        id = id,
        name = name,
        posterPath = poster_path
    )
}