package com.mertyazi.moviesappxml.details.data.mapper

import com.mertyazi.moviesappxml.details.data.dto.ResultDto
import com.mertyazi.moviesappxml.details.data.dto.VideosDto
import com.mertyazi.moviesappxml.details.domain.model.Videos
import com.mertyazi.moviesappxml.details.domain.model.Result

/**
 * Created by Mert Yazi
 */

fun VideosDto.toVideos(): Videos {
    return Videos(
        id = id,
        results = results.map { it.toResult() }
    )
}

fun ResultDto.toResult(): Result {
    return Result(
        id = id,
        iso31661 = iso_3166_1,
        iso6391 = iso_639_1,
        key = key,
        name = name,
        official = official,
        publishedAt = published_at,
        site = site,
        size = size,
        type = type
    )
}