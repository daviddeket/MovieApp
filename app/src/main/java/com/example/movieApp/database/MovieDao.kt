package com.example.movieApp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieApp.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Movie): Long

    @Update
    suspend fun update(favorite: Movie)

    @Query("DELETE FROM favorite_movies_table WHERE id = :movieId")
    suspend fun delete(movieId: Long)

    @Query("DELETE FROM favorite_movies_table")
    suspend fun clear()

    @Query("SELECT * FROM favorite_movies_table ORDER BY ID ASC")
    fun getAllFavoriteMovies(): LiveData<List<Movie>>
}