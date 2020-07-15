package com.mobiversal.movieapplication.movie

import androidx.room.*

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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