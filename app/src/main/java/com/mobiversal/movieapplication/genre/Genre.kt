package com.mobiversal.movieapplication.genre

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")

data class Genre(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String
) {
    override fun toString(): String {
        return "FavouriteGenre(id=$id, name='$name')"
    }

}