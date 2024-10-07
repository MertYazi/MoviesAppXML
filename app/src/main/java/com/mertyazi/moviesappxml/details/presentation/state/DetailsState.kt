package com.mertyazi.moviesappxml.details.presentation.state

import com.mertyazi.moviesappxml.details.domain.model.Details

/**
 * Created by Mert Yazi
 */

data class DetailsState(
    val details: Details = Details(),
    val loading: Boolean = false,
    val error: String = ""
)
