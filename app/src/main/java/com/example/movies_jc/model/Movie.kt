package com.example.movies_jc.model

import kotlinx.serialization.Serializable


@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int,
)
