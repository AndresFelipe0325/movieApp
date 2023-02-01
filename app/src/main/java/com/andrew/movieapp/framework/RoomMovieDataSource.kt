package com.andrew.movieapp.framework

import android.content.Context
import com.andrew.movieapp.core.data.Movie
import com.andrew.movieapp.core.repository.MovieDataSource
import com.andrew.movieapp.framework.db.DatabaseService
import com.andrew.movieapp.framework.db.MovieEntity
import com.andrew.movieapp.util.Category

class RoomMovieDataSource(context: Context): MovieDataSource {

    private val movieDao = DatabaseService.getInstance(context).movieDao()

    override suspend fun addMovie(movie: Movie) = movieDao.addMovieEntity(MovieEntity.fromMovie(movie))

    override suspend fun getMovies(category: String) = movieDao
        .getMoviesByCategory(Category.valueOf(category)).map { it?.toMovie() }
}