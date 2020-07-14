package com.mobiversal.movieapplication.movie

data class Movie(
    val id: Int,

    val title: String,

    var poster_path: String?,

    var release_date: String,

    var overview: String,

    var isFavorite: Boolean?,

    var isWatched: Boolean?
){
    override fun toString(): String {
        return "Movie(id=$id, titlu='$title', poster_path='$poster_path', release_date='$release_date', overview='$overview', isFavorite=$isFavorite, isWatched=$isWatched)"
    }
}