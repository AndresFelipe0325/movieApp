package com.andrew.movieapp.core.repository

import com.andrew.movieapp.core.data.User

interface UserDataSource {
    /** Methods to get information from user endpoint
     * NOTE: Using suspend fun because we're going to be applying coroutines
     * **/
    suspend fun addUser(user: User)

    suspend fun validateUser(username: String, password: String) : User
}