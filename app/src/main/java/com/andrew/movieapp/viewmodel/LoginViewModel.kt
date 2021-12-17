package com.andrew.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrew.movieapp.model.User

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val authSuccess = MutableLiveData<Boolean>()
    val authError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var users: List<User> = arrayListOf()

    init {
        authError.value = false
        loading.value = false
        authSuccess.value = false
        loadUsers()
    }

    private fun loadUsers(){
        users = listOf(User("af.moreno@globant.com", "12345"),
            User("sergio.meza@globant.com", "12345"))
    }


    fun logIn(){
        println("email: ${email.value}")
        println("password: ${password.value}")
        loading.value = true
        var userLoggedIn = User(email.value.toString(), password.value.toString())
        if(users.contains(userLoggedIn)){
            authSuccess.value = true
            loading.value = false
        }
        else {
            authError.value = true
            loading.value = false
        }
    }
}