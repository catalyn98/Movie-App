package com.mobiversal.movieapplication.genre

data class GenresDTO (val genres: List<GenreDTO>) {
    override fun toString(): String {
        return "GenresDTO(genres=$genres)"
    }

}