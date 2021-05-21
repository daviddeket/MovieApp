package com.example.movieApp.viewmodels

import androidx.lifecycle.*
import com.example.movieApp.models.Movie
import com.example.movieApp.repositories.MovieRepository
import kotlinx.coroutines.*

class MovieFavoritesViewModel (
    private val repository: MovieRepository
): ViewModel() {

    val allMovies: LiveData<List<Movie>> = repository.getAllFavoriteMovies()

    fun onInsert(favmovie: Movie) {
        viewModelScope.launch {
            repository.insert(favmovie)
        }
    }

    fun onDelete(movieId: Long) {
        viewModelScope.launch {
            repository.delete(movieId)
        }
    }

    fun onUpdate(favmovie: Movie) {
        viewModelScope.launch {
            repository.update(favmovie)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            repository.clear()
        }
    }
}