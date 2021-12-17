package com.andrew.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrew.movieapp.R
import com.andrew.movieapp.model.Movie
import com.andrew.movieapp.util.BASE_URL_IMG
import com.andrew.movieapp.util.getProgressDrawable
import com.andrew.movieapp.util.loadImage
import kotlinx.android.synthetic.main.item_movie_main.view.*

class MovieList(private var movies: ArrayList<Movie>): RecyclerView.Adapter<MovieList.MovieViewHolder>() {


    fun updateMovies(newMovies: List<Movie>){
        movies.clear()
        movies.addAll(newMovies)
        println("Update movies: ${movies.size}")
        notifyDataSetChanged()

    }

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val movieLogo = view.movie_image
        private val movieTitle = view.movie_title
        private val movieSynopsis = view.movie_synopsis
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(movie: Movie){
            movieLogo.loadImage("$BASE_URL_IMG${movie.imgUrl}", progressDrawable)
            movieTitle.text = movie.title
            movieSynopsis.text = movie.synopsis
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main, parent, false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

}