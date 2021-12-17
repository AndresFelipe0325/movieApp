package com.andrew.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val authError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        authError.value = false
        loading.value = false
    }


    fun logIn(){
        println("email: ${email.value}")
        println("password: ${password.value}")
    }

    override fun onCleared() {
        super.onCleared()
    }
}