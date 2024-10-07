package com.mertyazi.moviesappxml.details.domain.model

data class Videos(
    val id: Int = 0,
    val results: List<Result> = listOf()
)