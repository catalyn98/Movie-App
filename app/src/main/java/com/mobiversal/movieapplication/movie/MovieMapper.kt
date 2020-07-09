package com.mobiversal.movieapplication.movie

class MovieMapper {
    fun map(dto: MovieDTO): Movie {
        return Movie(
            id= dto.id,
            title = dto.title,
            poster_path = dto.poster_path,
            release_date = dto.release_date,
            overview = dto.overview,
            isFavorite = dto.isFavorite,
            isWatched = dto.isWatched
        )
    }
}