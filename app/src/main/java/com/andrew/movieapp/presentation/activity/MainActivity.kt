package com.andrew.movieapp.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrew.movieapp.R
import com.andrew.movieapp.adapter.MovieList
import com.andrew.movieapp.databinding.ActivityMainBinding
import com.andrew.movieapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val moviesAdapter = MovieList(arrayListOf())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        movies_list.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.popularMovies()

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_popular -> {
                    viewModel.popularMovies()
                    setTitle("Popular Movies")
                }
                R.id.ic_top_rated -> {
                    viewModel.topRatedMovies()
                    setTitle("Top Rated Movies")
                }
                R.id.ic_upcoming -> {
                    viewModel.upcomingMovies()
                    setTitle("Upcoming Movies")
                }
            }
            true
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.moviesList.observe(this, { movies ->
            movies?.let {
                movies_list.visibility = View.VISIBLE
                moviesAdapter.updateMovies(it)
            }
        })

        viewModel.loadingMovie.observe(this, {
            it?.let {
                loading_movie.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    error_loading_movie.visibility = View.GONE
                    movies_list.visibility = View.GONE
                }
            }
        })

        viewModel.loadingMovieError.observe(this, {
            it?.let {
                error_loading_movie.visibility = if(it) View.VISIBLE else View.GONE
            }
        })
    }
}