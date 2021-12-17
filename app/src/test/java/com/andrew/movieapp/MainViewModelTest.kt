package com.andrew.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andrew.movieapp.model.Movie
import com.andrew.movieapp.model.MoviesResponse
import com.andrew.movieapp.model.MoviesService
import com.andrew.movieapp.viewmodel.MainViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class MainViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesService: MoviesService

    @InjectMocks
    var viewModel = MainViewModel()


    private var testSingle: Single<MoviesResponse>? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object: Scheduler(){
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker( { it.run() } )
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun getMoviesSuccess() {
        val movie = Movie(908, "Shun-li", "The street figthers movie", "url")
        val movieList = arrayListOf(movie)
        val movieResponse = MoviesResponse(1, movieList, 20, 200)

        testSingle = Single.just(movieResponse)

        `when`(moviesService.getPopularMovies()).thenReturn(testSingle)

        viewModel.refresh()

        Assert.assertEquals(1, viewModel.moviesList.value?.size)
        Assert.assertEquals(false, viewModel.loadingMovieError.value)
        Assert.assertEquals(false, viewModel.loadingMovie.value)
    }

    @Test
    fun getMoviesFail(){
        testSingle = Single.error(Throwable())

        `when`(moviesService.getPopularMovies()).thenReturn(testSingle)

        viewModel.refresh()

        Assert.assertEquals(true, viewModel.loadingMovieError.value)
        Assert.assertEquals(false, viewModel.loadingMovie.value)

    }
}