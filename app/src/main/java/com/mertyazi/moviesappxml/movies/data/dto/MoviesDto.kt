package com.mertyazi.moviesappxml.movies.data.dto

data class MoviesDto(
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)