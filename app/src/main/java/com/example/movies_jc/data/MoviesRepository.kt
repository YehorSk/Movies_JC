package com.example.movies_jc.data

import com.example.movies_jc.model.Movie
import com.example.movies_jc.model.MovieDetails

interface MoviesRepository {
    suspend fun getMovies() : List<Movie>

    suspend fun getMovie(id: String) : MovieDetails
}