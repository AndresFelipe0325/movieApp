package com.andrew.movieapp.core.usecase.movie

import com.andrew.movieapp.core.data.Movie
import com.andrew.movieapp.core.repository.MovieRepository

class AddMovie(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movie: Movie) = movieRepository.addMovie(movie)
}