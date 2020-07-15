package com.mobiversal.movieapplication.ui.save

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mobiversal.movieapplication.MainActivity
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.actor.ActorsRepository
import com.mobiversal.movieapplication.actor.FavouriteActor
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.genre.GenreRepository
import com.mobiversal.movieapplication.movie.Movie
import com.mobiversal.movieapplication.movie.MoviesRepository
import com.mobiversal.movieapplication.ui.search.MoviesAdapter
import com.mobiversal.movieapplication.ui.search.SearchMovieInteractionListener
import com.mobiversal.movieapplication.ui.search.SearchMoviesFragment
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveFragment : Fragment() , SearchMovieInteractionListener {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_save, container, false)
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val savedMoviesTabLayout : TabLayout = view.findViewById(R.id.tabLayout)
    val savedMoviesViewPager : ViewPager = view.findViewById(R.id.viewPager)
    val adapter: SavedMoviesPagerAdapter = SavedMoviesPagerAdapter(view.context, childFragmentManager)
    savedMoviesViewPager.adapter = adapter
    savedMoviesTabLayout.setupWithViewPager(savedMoviesViewPager)
  }

  companion object {
    private val TAG = SearchMoviesFragment::class.java.simpleName
  }

  private val movieRepository = MoviesRepository.instance
  private val genreRepository = GenreRepository.instance
  private val actorRepository = ActorsRepository.instance

  private var selectedGenres: List<Genre> = emptyList()
  private var selectedActors: List<FavouriteActor> = emptyList()

  private var hasActors = false
  private var hasGenres = false

  private var adapter: MoviesAdapter?= null

  fun getMovies() {
    GlobalScope.launch(Dispatchers.IO) {
      getSelectedActors()
      getSelectedGenres()
    }
  }

  private fun getSelectedGenres() {
    GlobalScope.launch(Dispatchers.IO) {
      selectedGenres = genreRepository.getAll()
      hasGenres = true
      checkRequestAndSend()
    }
  }

  private fun getSelectedActors() {
    GlobalScope.launch(Dispatchers.IO) {
      selectedActors = actorRepository.getAll()
      hasActors = true
      checkRequestAndSend()
    }
  }

  private fun checkRequestAndSend() {
    if (hasActors && hasGenres) {
      GlobalScope.launch(Dispatchers.IO) {
        val movies = movieRepository.getAll()
        withContext(Dispatchers.Main) {
          setupRecyclerView(movies)
        }
      }
    }
  }

  private fun convertGenreListToString(genreList: List<Genre>): String {
    val selectedGenresIds: MutableList<Int> = mutableListOf()
    for (genre in genreList) {
      selectedGenresIds.add(genre.id)
    }
    return selectedGenresIds.joinToString("|")
  }

  private fun convertActorListToString(actorList: List<FavouriteActor>): String {
    val selectedActorIds: MutableList<Int> = mutableListOf()
    for (actor in actorList) {
      selectedActorIds.add(actor.id)
    }
    return selectedActorIds.joinToString("|")
  }

  private fun setupRecyclerView(movies: List<Movie>) {
    iv_movie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.adapter = MoviesAdapter(movies, this)
    iv_movie.adapter = adapter
  }

  override fun updateMovie(movie: Movie) {
    GlobalScope.launch{
      movieRepository.save(movie)
      withContext(Dispatchers.Main){
        adapter?.notifyDataSetChanged()
      }
    }
  }
  }
