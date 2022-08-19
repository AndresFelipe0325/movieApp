package com.andrew.movieapp.core.repository

interface MovieDataSource {
    /** Methods to get information from movie endpoint
     * NOTE: Using suspend fun because we're going to be applying coroutines
     * **/
    suspend fun getPopularMovies()

    suspend fun getTopRatedMovies()

    suspend fun getUpcomingMovies()

}