package com.example.movies_jc.data

import com.example.movies_jc.model.Movie

interface MoviesRepository {
    suspend fun getMovies() : List<Movie>
}