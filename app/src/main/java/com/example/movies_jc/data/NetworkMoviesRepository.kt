package com.example.movies_jc.data

import com.example.movies_jc.model.Movie
import com.example.movies_jc.network.MoviesApiService

class NetworkMoviesRepository(
    private val moviesApiService: MoviesApiService
) : MoviesRepository {

    override suspend fun getMovies(): List<Movie> {
        return moviesApiService.getMovies().results
    }

}