package com.mertyazi.moviesappxml.movies.domain.model

/**
 * Created by Mert Yazi
 */

data class Movies(
    val page: Int = 0,
    val results: List<Result> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
