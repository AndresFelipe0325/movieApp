package com.andrew.movieapp.model

import com.andrew.movieapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject


class MoviesService {

    @Inject
    lateinit var api: MoviesApi

    init{
        DaggerApiComponent.create().inject(this)
    }

    fun getPopularMovies(): Single<MoviesResponse> {
        return api.getPopularMovies()
    }

    fun getTopRatedMovies(): Single<MoviesResponse> {
        return api.getTopRatedMovies()
    }

    fun getUpcomingMovies(): Single<MoviesResponse> {
        return api.getUpcomingMovies()
    }
}