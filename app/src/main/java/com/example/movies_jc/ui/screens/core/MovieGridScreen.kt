package com.example.movies_jc.ui.screens.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies_jc.model.Movie

@Composable
fun MovieGridScreen(onSelectMovie: (Movie) -> Unit, movies: List<Movie>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        items(items = movies, key = {movie -> movie.id}){ movie ->
            MovieCard(
                photo = movie,
                onMovieClick = {onSelectMovie(movie)},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}