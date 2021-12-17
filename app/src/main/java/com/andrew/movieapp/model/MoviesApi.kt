package com.andrew.movieapp.model


import io.reactivex.Single
import retrofit2.http.GET


interface MoviesApi {

    @GET("/3/movie/popular")
    fun getPopularMovies(): Single<MoviesResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(): Single<MoviesResponse>

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(): Single<MoviesResponse>
}