package com.mobiversal.movieapplication.ui.search

import com.mobiversal.movieapplication.movie.Movie

interface SearchMovieInteractionListener {

    fun addToFavorite(movie: Movie)

    fun removeFromFavorite(movie: Movie)

    fun addWatched(movie: Movie)

    fun removeWatched(movie: Movie)
}