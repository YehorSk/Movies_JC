package com.example.movies_jc.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies_jc.R
import com.example.movies_jc.model.Movie

@Composable
fun HomeScreen(moviesUiState: MoviesUiState,
               moviesViewModel: MoviesViewModel,
               retryAction: () -> Unit,
               modifier: Modifier = Modifier){
    when(moviesUiState){
        is MoviesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MoviesUiState.Success -> MovieGridScreen(movies = moviesUiState.movies, onSelectMovie = {moviesViewModel.selectMovie(it)},modifier = modifier)
        is MoviesUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun MovieCard(photo: Movie, onMovieClick: () -> Unit, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.padding(4.dp),
        onClick = onMovieClick,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://image.tmdb.org/t/p/original"+photo.poster_path)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            modifier = modifier
        )
    }
}

@Composable
fun MovieGridScreen(onSelectMovie: (Movie) -> Unit,movies: List<Movie>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)){
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
