package com.mobiversal.movieapplication.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDTO(
    val id: Int,
    val title: String,
    @Json(name="poster_path")
    val poster_path: String,
    var release_date: String,
    var overview: String,
    var isFavorite: Boolean?,
        var isWatched: Boolean?) {
    override fun toString(): String {
        return "MovieDTO(id=$id, titlu='$title', poster_path='$poster_path', release_date='$release_date', overview='$overview', isFavorite=$isFavorite, isWatched=$isWatched)"
    }
}