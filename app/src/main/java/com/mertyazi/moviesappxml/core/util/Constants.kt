package com.mertyazi.moviesappxml.core.util

/**
 * Created by Mert Yazi
 */

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = "0b33edc6641b27d29bf34f38df427b1d"
    const val API_KEY_QUERY = "api_key"
    const val MOVIE_ID_PATH = "movie_id"
    const val PAGE_QUERY = "page"
    const val TRAILER = "Trailer"

    fun dateFormatter(value: String?): String {
        if (value != null) {
            val dateArray: List<String> = value.split("-")
            return dateArray[2] + "." + dateArray[1] + "." + dateArray[0]
        } else {
            return ""
        }
    }

    fun pointsCalculator(value: Double?, voteCount: Int): String {
        if (value != null) {
            return (value.toFloat() / 2).toString().substring(0, 3) +
                    " out of " + voteCount + " votes"
        } else {
            return ""
        }
    }
}