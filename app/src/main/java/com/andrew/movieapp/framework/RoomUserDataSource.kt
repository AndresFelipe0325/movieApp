package com.andrew.movieapp.framework

import android.content.Context
import com.andrew.movieapp.core.data.User
import com.andrew.movieapp.core.repository.UserDataSource
import com.andrew.movieapp.framework.db.DatabaseService
import com.andrew.movieapp.framework.db.UserEntity

class RoomUserDataSource(context: Context) : UserDataSource{

    private val userDao = DatabaseService.getInstance(context).userDao()

    override suspend fun addUser(user: User) = userDao
        .addUserEntity(UserEntity.fromUser(user))

    override suspend fun validateUser(username: String, password: String) = userDao
        .getUser(username, password).toUser()
}