package com.mobiversal.movieapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.mobiversal.movieapplication.actors_list.Actors
import com.mobiversal.movieapplication.genre.GenreRepository
import com.mobiversal.movieapplication.genres_list.Genres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    private val genresRepository = GenreRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actorsButton : Button = findViewById(R.id.actors)
        actorsButton.setOnClickListener{
            val intent = Intent (this, Actors::class.java)
            startActivity(intent)
        }

        val genresButton : Button = findViewById(R.id.genres)
        genresButton.setOnClickListener {
            val intent = Intent(this, Genres::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "onCreate()")

        GlobalScope.launch(Dispatchers.IO) {
            val genres = genresRepository.getAllRemote()
            genresRepository.replaceAll(genres)
            withContext(Dispatchers.Main){
                Log.d(TAG, genres.first().name)
            }
        }
        }
    }