package com.andrew.movieapp.core.usecase.movie

import com.andrew.movieapp.core.repository.MovieRepository

class GetUpcomingMovies(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() = movieRepository.getUpcomingMovies()
}