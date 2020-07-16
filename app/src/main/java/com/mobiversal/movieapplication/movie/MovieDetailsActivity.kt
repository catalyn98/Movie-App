package com.mobiversal.movieapplication.movie

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobiversal.movieapplication.network.Constants.KEY_MOVIE_ID

class MovieDetailsActivity : AppCompatActivity() {

    companion object{
        private val TAG = MovieDetailsActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.let {bundle ->
            val movieID = bundle.getInt(KEY_MOVIE_ID, -1)

            if (movieID == -1) error("Invalid movie id")

            Log.d(TAG, "Movie id is: $movieID")
        }
    }
}