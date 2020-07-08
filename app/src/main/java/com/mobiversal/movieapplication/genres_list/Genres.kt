package com.mobiversal.movieapplication.genres_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.genre.GenreRepository
import kotlinx.android.synthetic.main.activity_genres.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Genres : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private var genres: List<Genre>? = null
    val list: List<Genre> = ArrayList()

    companion object {
        val TAG = Genres::class.java.simpleName
    }

    private fun setupRecycleView() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_genres.layoutManager = llm
        rv_genres.adapter = GenresAdapter(genres)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)
        getGenres()
        Log.d(Genres.TAG, genres?.firstOrNull()?.name?: "not found")
        setupRecycleView()
    }

    fun getGenres(): List<Genre>? {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genreRepository.getAllRemote()
            genres?.let { genres ->
                genreRepository.replaceAll(genres)
                withContext(Dispatchers.Main) {
                    setupRecycleView()
                }
            }
        }
        return genres
    }
}