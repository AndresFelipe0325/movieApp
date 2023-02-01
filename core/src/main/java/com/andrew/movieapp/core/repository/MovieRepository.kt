package com.andrew.movieapp.core.repository

import com.andrew.movieapp.core.data.Movie

//TODO: HEREDAR DE LA INTERFAZ QUE SE CREE PARA EL REPOSITORIO
class MovieRepository(private val movieDataSource: MovieDataSource) {

    suspend fun addMovie(movie: Movie) = movieDataSource.addMovie(movie)

    suspend fun getMovies(category: String) = movieDataSource.getMovies(category)
}