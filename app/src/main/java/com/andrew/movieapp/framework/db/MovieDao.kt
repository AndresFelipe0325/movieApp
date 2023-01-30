package com.andrew.movieapp.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun addMovieEntity(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieEntity(id: Int): MovieEntity?

    @Query("SELECT * FROM movie")
    suspend fun getAllMovieEntities(): List<MovieEntity>

    @Delete
    suspend fun deleteMovieEntity(movieEntity: MovieEntity)
}