package com.andrew.movieapp.ui

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

        binding.moviesList.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_popular -> {
                    viewModel.popularMovies()
                    title = "Popular Movies"
                }
                R.id.ic_top_rated -> {
                    viewModel.topRatedMovies()
                    title = "Top Rated Movies"
                }
                R.id.ic_upcoming -> {
                    viewModel.upcomingMovies()
                    title = "Upcoming Movies"
                }
            }
            true
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.moviesList.observe(this) { movies ->
            movies?.let {
                binding.moviesList.visibility = View.VISIBLE
                moviesAdapter.updateMovies(it)
            }
        }

        viewModel.loadingMovie.observe(this) {
            it?.let {
                binding.loadingMovie.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.errorLoadingMovie.visibility = View.GONE
                    binding.moviesList.visibility = View.GONE
                }
            }
        }

        viewModel.loadingMovieError.observe(this) {
            it?.let {
                binding.errorLoadingMovie.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}