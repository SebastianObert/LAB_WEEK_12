package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "7c3f9513fa6db43815ebb38fb8b36e3e"

    // StateFlow untuk list movie
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>>
        get() = _movies

    // StateFlow untuk error message
    private val _error = MutableStateFlow("")
    val error: StateFlow<String>
        get() = _error

    // Fungsi fetch movies dengan try-catch block
    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            _movies.value = popularMovies.results
        } catch (exception: Exception) {
            _error.value = "An error occurred: ${exception.message}"
        }
    }
}