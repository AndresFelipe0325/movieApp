package com.andrew.movieapp.core.repository

class MovieRepository(private val movieDataSource: MovieDataSource) {

    suspend fun getPopularMovies() = movieDataSource.getPopularMovies()

    suspend fun getTopRatedMovies() = movieDataSource.getTopRatedMovies()

    suspend fun getUpcomingMovies() = movieDataSource.getUpcomingMovies()
}