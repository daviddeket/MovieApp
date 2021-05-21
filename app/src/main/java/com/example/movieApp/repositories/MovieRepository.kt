package com.example.movieApp.repositories

import com.example.movieApp.database.MovieDao
import com.example.movieApp.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {
    fun getAllFavoriteMovies() = movieDao.getAllFavoriteMovies()

    suspend fun insert(favorite: Movie): Long = withContext(Dispatchers.IO) {
        movieDao.insert(favorite)
    }

    suspend fun delete(movieId: Long) = withContext(Dispatchers.IO) {
        movieDao.delete(movieId)
    }

    suspend fun update(favorite: Movie) = withContext(Dispatchers.IO) {
        movieDao.update(favorite)
    }

    suspend fun clear() = withContext(Dispatchers.IO) {
        movieDao.clear()
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}