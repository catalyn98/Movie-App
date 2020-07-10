package com.mobiversal.movieapplication.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.MainActivity
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.genres_list.Genres
import com.mobiversal.movieapplication.movie.Movie
import com.mobiversal.movieapplication.movie.MoviesRepository
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMoviesFragment : Fragment() {
    private val movieRepository = MoviesRepository.instance
    fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getAllRemote()
            withContext(Dispatchers.Main) {
                setupRecyclerView(movies)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovies()
        val preferencesButton : Button = view.findViewById(R.id.button_preferences_from_movie_list)
        preferencesButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        iv_movie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        iv_movie.adapter = MoviesAdapter(movies)
    }
}