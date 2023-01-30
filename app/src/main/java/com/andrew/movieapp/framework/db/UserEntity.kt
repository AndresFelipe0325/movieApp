package com.andrew.movieapp.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.movieapp.core.data.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String
){
    companion object{
        fun fromUser(user: User) = UserEntity(username = user.username, password = user.password)
    }

    fun toUser() = User(id, username, password)
}
