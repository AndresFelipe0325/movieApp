package com.andrew.movieapp.model


data class MoviesResponse(
    val page: Int? = 0,
    val results: List<Movie> = arrayListOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)
