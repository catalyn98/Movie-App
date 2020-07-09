package com.mobiversal.movieapplication.movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MoviesDTO>

}