package com.andrew.movieapp.core.repository

class UserRepository (private val userDataSource: UserDataSource){

    suspend fun validateUser(username: String, password: String) =
        userDataSource.validateUser(username, password)
}