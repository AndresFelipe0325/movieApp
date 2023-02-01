package com.andrew.movieapp.core.repository

import com.andrew.movieapp.core.data.User

class UserRepository (private val userDataSource: UserDataSource){

    suspend fun addUser(user: User) = userDataSource.addUser(user)

    suspend fun validateUser(username: String, password: String) =
        userDataSource.validateUser(username, password)
}