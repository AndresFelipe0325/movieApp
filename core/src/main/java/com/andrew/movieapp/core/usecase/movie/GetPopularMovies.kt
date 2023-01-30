package com.andrew.movieapp.core.usecase.movie

import com.andrew.movieapp.core.repository.MovieRepository

//TODO: QUITAR LA DEPENDENCIA CON MOVIEREPOSITORY - TEST UNITARIOS
class GetPopularMovies(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() = movieRepository.getPopularMovies()
}