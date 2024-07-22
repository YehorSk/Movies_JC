package com.example.movies_jc.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movies_jc.ui.screens.core.MovieGridScreen


@Composable
fun HomeScreen(moviesUiState: MoviesUiState,
               moviesViewModel: MoviesViewModel,
               retryAction: () -> Unit,
               onNavigateAction: () -> Unit,
               modifier: Modifier = Modifier){
    when(moviesUiState){
        is MoviesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MoviesUiState.Success -> MovieGridScreen(
            movies = moviesUiState.movies,
            onSelectMovie = {
            moviesViewModel.selectMovie(it)
            onNavigateAction() },
            modifier = modifier)
        is MoviesUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}
