package com.andrew.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrew.movieapp.di.DaggerApiComponent
import com.andrew.movieapp.model.Movie
import com.andrew.movieapp.model.MoviesResponse
import com.andrew.movieapp.model.MoviesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel: ViewModel() {

    @Inject
    lateinit var moviesService: MoviesService

    private val disposable = CompositeDisposable()

    /** Main items **/
    val moviesList = MutableLiveData<List<Movie>?>()
    val loadingMovieError = MutableLiveData<Boolean>()
    val loadingMovie = MutableLiveData<Boolean>()

    init{
        DaggerApiComponent.create().inject(this)
    }

    fun popularMovies(){
        loadingMovie.value = true
        disposable.add(
            moviesService.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MoviesResponse>(){
                    override fun onSuccess(value: MoviesResponse?) {
                        moviesList.value = value?.results
                        loadingMovie.value = false
                        loadingMovieError.value = false
                        println("Success: list: ${moviesList.value}")
                        println("Success: size: ${moviesList.value?.size}")
                    }

                    override fun onError(e: Throwable?) {
                        loadingMovieError.value = true
                        loadingMovie.value = false
                        println("Error: ${e.toString()} ")
                        println("Error: ${e?.message} ")
                        e?.printStackTrace()
                    }
                })
        )
    }


    fun upcomingMovies(){
        loadingMovie.value = true
        disposable.add(
            moviesService.getUpcomingMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MoviesResponse>(){
                    override fun onSuccess(value: MoviesResponse?) {
                        moviesList.value = value?.results
                        loadingMovie.value = false
                        loadingMovieError.value = false
                        println("Success: list: ${moviesList.value}")
                        println("Success: size: ${moviesList.value?.size}")
                    }

                    override fun onError(e: Throwable?) {
                        loadingMovieError.value = true
                        loadingMovie.value = false
                        println("Error: ${e.toString()} ")
                        println("Error: ${e?.message} ")
                        e?.printStackTrace()
                    }
                })
        )
    }

    fun topRatedMovies(){
        loadingMovie.value = true
        disposable.add(
            moviesService.getTopRatedMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MoviesResponse>(){
                    override fun onSuccess(value: MoviesResponse?) {
                        moviesList.value = value?.results
                        loadingMovie.value = false
                        loadingMovieError.value = false
                        println("Success: list: ${moviesList.value}")
                        println("Success: size: ${moviesList.value?.size}")
                    }

                    override fun onError(e: Throwable?) {
                        loadingMovieError.value = true
                        loadingMovie.value = false
                        println("Error: ${e.toString()} ")
                        println("Error: ${e?.message} ")
                        e?.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}