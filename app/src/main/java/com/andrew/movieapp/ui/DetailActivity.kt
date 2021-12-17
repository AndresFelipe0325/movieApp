package com.andrew.movieapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.andrew.movieapp.R
import com.andrew.movieapp.databinding.ActivityDetailBinding
import com.andrew.movieapp.viewmodel.DetailViewModel

class DetailActivity: AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    fun setupView(){
        setTitle("Detail")
        println("Va SetupView")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detailVM = viewModel
        val movieDataparts = intent.getStringExtra("movieInfo")?.split("/")
        val title = movieDataparts?.get(1)
        val synopsis = movieDataparts?.get(2)
        println("data: $movieDataparts")
        println("title: $title, synopsis: $synopsis")
        binding.movieTitle2.text = title
        binding.synopsis.text = synopsis
        viewModel.movieTitle.value = title
        viewModel.movieSynopsis.value = synopsis
    }
}