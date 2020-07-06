package com.mobiversal.movieapplication.genre

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDTO(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return "GenreDTO(id=$id, name='$name')"
    }
}