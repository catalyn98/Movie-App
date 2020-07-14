package com.mobiversal.movieapplication.movie

import androidx.room.*
import com.mobiversal.movieapplication.actor.FavouriteActor

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>
    @Insert
    fun save(movie: Movie)
    @Insert
    fun saveAll(movie: List<Movie>)
    @Delete
    fun delete(movie: Movie)
    @Query("DELETE FROM movies")
    fun deleteAll()
    @Delete
    fun deleteAll(movie: List<Movie>)
    @Delete
    fun deleteMovie(movie: Movie)
    @Delete
    fun deleteFavoriteMovie(favoriteMovie: Movie)
    @Transaction
    fun replaceAll(movie: List<Movie>) {
        deleteAll()
        saveAll(movie)
    }
}