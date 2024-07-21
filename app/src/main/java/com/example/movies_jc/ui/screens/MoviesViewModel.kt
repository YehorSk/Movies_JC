package com.example.movies_jc.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movies_jc.MoviesApplication
import com.example.movies_jc.data.MoviesRepository
import com.example.movies_jc.data.NetworkMoviesRepository
import com.example.movies_jc.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MoviesUiState{
    data class Success(val movies: List<Movie>) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}

data class SelectedMovieUiState(
    val movie: Movie? = null
)

class MoviesViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    private val _selectedMovieUiState = MutableStateFlow(SelectedMovieUiState())
    val selectedMovieUiState: StateFlow<SelectedMovieUiState> = _selectedMovieUiState.asStateFlow()


    init {
        getMovies()
    }

    fun selectMovie(movie: Movie){
        _selectedMovieUiState.update { currentState ->
            currentState.copy(movie = movie)
        }
        Log.d("Movie",movie.title)
    }

    fun getMovies(){
        viewModelScope.launch {
            moviesUiState = try{
                MoviesUiState.Success(moviesRepository.getMovies())
            }catch (e: IOException){
                MoviesUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MoviesApplication)
                val moviesRepository = application.container.moviesRepository
                MoviesViewModel(moviesRepository = moviesRepository)
            }
        }
    }

}