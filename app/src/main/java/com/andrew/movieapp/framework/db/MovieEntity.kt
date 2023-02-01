package com.andrew.movieapp.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.movieapp.core.data.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val synopsis: String?,
    val imgURL: String?,
    val category: String?
){
    companion object{
        fun fromMovie(movie: Movie) = MovieEntity(
            title = movie.title,
            synopsis = movie.synopsis,
            imgURL = movie.imgUrl,
            category = movie.category)
    }

    fun toMovie() = Movie(id, title, synopsis, imgURL, category)
}
