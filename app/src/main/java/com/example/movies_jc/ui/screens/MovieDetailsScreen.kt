package com.example.movies_jc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies_jc.R
import com.example.movies_jc.model.MovieDetails
import com.example.movies_jc.ui.screens.core.MovieGridScreen
import com.example.movies_jc.ui.theme.Movies_JCTheme

@Composable
fun MovieDetailsScreen(
    movieDetailsUiState: MovieDetailsUiState,
    moviesViewModel: MoviesViewModel = viewModel(),
    retryAction: () -> Unit,
    onNavigateAction: () -> Unit = {},
    modifier: Modifier = Modifier
){
    when(movieDetailsUiState){
        is MovieDetailsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MovieDetailsUiState.Success -> MovieDetails(
            movie = movieDetailsUiState.movie,
            modifier = modifier)
        is MovieDetailsUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }

}

@Composable
fun MovieDetails(movie: MovieDetails,modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState()).fillMaxWidth()
    ) {
        MovieImage(
            movie = movie)
    }
}

@Composable
fun MovieImage(movie: MovieDetails, modifier: Modifier = Modifier){
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://image.tmdb.org/t/p/original"+movie.posterPath)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            modifier = Modifier.width(320.dp)
        )
    }
}
