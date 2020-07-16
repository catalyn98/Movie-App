package com.mobiversal.movieapplication.movie

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobiversal.movieapplication.network.Constants.KEY_MOVIE_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsActivity : AppCompatActivity() {

    companion object{
        private val TAG = MovieDetailsActivity::class.java.simpleName
    }

    private val moviesRepository: MoviesRepository = MoviesRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.let {bundle ->
            val movieID = bundle.getInt(KEY_MOVIE_ID, -1)

            if (movieID == -1) error("Invalid movie id")

            Log.d(TAG, "Movie id is: $movieID")
            fetchMoviesDetails(movieID)
        }
    }

    private fun onMovieDetailsReady(movie: Movie) {
        Log.d(TAG, "Movie id is: $movie")
    }

    private fun fetchMoviesDetails(movieID: Int){
        GlobalScope.launch{
            val movie:  Movie = moviesRepository.getMoviesDetails(movieID)

            withContext(Dispatchers.Main){
                onMovieDetailsReady(movie)
            }
        }
    }
}