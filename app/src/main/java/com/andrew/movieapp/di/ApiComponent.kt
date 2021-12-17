package com.andrew.movieapp.di

import com.andrew.movieapp.model.MoviesService
import com.andrew.movieapp.viewmodel.MainViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: MoviesService)

    fun inject(viewModel: MainViewModel)
}
