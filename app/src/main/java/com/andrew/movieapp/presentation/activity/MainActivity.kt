package com.andrew.movieapp.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.andrew.movieapp.R
import com.andrew.movieapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*private val viewModel: MainViewModel by viewModels()
    private val moviesAdapter = MovieList(arrayListOf())*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView(){
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.navMainHostFragment)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            binding.bottomNavigation.visibility =
                if (nd.id == R.id.loginFragment) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
        /*movies_list.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.popularMovies()
        title = "Popular Movies"

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_popular -> {
                }
                R.id.ic_top_rated -> {
                }
                R.id.ic_upcoming -> {
                }
            }
            true
        }*/

        //observeViewModel()
    }

    /*private fun observeViewModel(){
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
    }*/
}