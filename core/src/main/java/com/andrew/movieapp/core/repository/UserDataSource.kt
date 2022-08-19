package com.andrew.movieapp.core.repository

interface UserDataSource {
    /** Methods to get information from user endpoint
     * NOTE: Using suspend fun because we're going to be applying coroutines
     * **/
    suspend fun validateUser(username: String, password: String)
}