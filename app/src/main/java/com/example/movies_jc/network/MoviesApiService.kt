package com.example.movies_jc.network

import com.example.movies_jc.model.MovieDetails
import com.example.movies_jc.model.MovieResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val API_KEY = "5dea5d58328fe45856d4e5d4c92ef494"

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String = API_KEY,@Query("page") page: Int = 1): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: String, @Query("api_key") apiKey: String = API_KEY) : MovieDetails

}

