package com.mobiversal.movieapplication.movie

import com.mobiversal.movieapplication.network.APIClient

class MoviesRepository private constructor() {
    companion object {
        val instance = MoviesRepository()
    }
    private val movieRemoteDataSource = MovieRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )
    @Throws(Exception::class)
    suspend fun getAllRemote() = movieRemoteDataSource.getMovies()
}

//object MoviesRepository {
//    private val movieRemoteDataSource = MovieRemoteDataSource(
//        retrofit = APIClient.instance.retrofit
//    )
//
//    @Throws(Exception::class)
//    suspend fun getAllRemote() = MoviesRepository.movieRemoteDataSource.getMovies()
//}