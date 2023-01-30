package com.andrew.movieapp.core.repository

//TODO: HEREDAR DE LA INTERFAZ QUE SE CREE PARA EL REPOSITORIO
class MovieRepository(private val movieDataSource: MovieDataSource) {

    suspend fun getPopularMovies() = movieDataSource.getPopularMovies()

    suspend fun getTopRatedMovies() = movieDataSource.getTopRatedMovies()

    suspend fun getUpcomingMovies() = movieDataSource.getUpcomingMovies()
}