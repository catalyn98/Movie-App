package com.mobiversal.movieapplication.movie

import android.util.Log
import com.mobiversal.movieapplication.network.Constants
import com.mobiversal.movieapplication.network.executeAndDeliver
import retrofit2.Retrofit
import java.lang.Exception

class MovieRemoteDataSource(retrofit: Retrofit) {

    private val apiService : MovieAPIService = retrofit.create(MovieAPIService::class.java)
    private val movieMapper  = MovieMapper()

    @Throws(Exception::class)
    fun getMovies(): List<Movie>{

        return apiService.getMovies(Constants.API_KEY, Constants.LANGUAGE)
            .executeAndDeliver()
            .results
            .map { movieMapper.map(it) }
    }
}