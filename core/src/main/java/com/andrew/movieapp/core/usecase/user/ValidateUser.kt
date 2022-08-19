package com.andrew.movieapp.core.usecase.user

import com.andrew.movieapp.core.repository.UserRepository

class ValidateUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(username: String, password:String) =
        userRepository.validateUser(username, password)
}