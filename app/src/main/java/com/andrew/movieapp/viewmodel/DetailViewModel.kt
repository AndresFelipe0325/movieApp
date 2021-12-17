package com.andrew.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {

    val movieTitle = MutableLiveData<String>()
    val movieSynopsis = MutableLiveData<String>()


}