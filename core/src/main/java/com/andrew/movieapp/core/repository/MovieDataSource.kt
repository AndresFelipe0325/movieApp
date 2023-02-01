package com.andrew.movieapp.core.repository

import com.andrew.movieapp.core.data.Movie


//TODO: IMPLEMENTAR EL DATASOURCE
interface MovieDataSource {
    /** Methods to get information from movie endpoint
     * NOTE: Using suspend fun because we're going to be applying coroutines
     * **/

    suspend fun addMovie(movie: Movie)

    suspend fun getMovies(category: String) : List<Movie?>

}