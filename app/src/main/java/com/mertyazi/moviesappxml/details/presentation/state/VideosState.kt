package com.mertyazi.moviesappxml.details.presentation.state

import com.mertyazi.moviesappxml.details.domain.model.Videos

/**
 * Created by Mert Yazi
 */

data class VideosState(
    val videos: Videos = Videos(),
    val loading: Boolean = false,
    val error: String = ""
)
