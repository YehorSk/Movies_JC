package com.example.movies_jc.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies_jc.R
import com.example.movies_jc.model.Genre
import com.example.movies_jc.model.MovieDetails
import java.util.Random

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
fun MovieTitle(title: String, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun MovieDetails(movie: MovieDetails,modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        MovieTitle(
            title = movie.title,
            modifier = Modifier.padding(16.dp)
        )
        MovieImage(
            movie = movie,
            modifier = Modifier.padding(16.dp))
        GenreGrid(
            movie.genres,
            modifier = Modifier.padding(16.dp)
            )
        MovieOverview(
            movie.overview,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MovieOverview(overview: String,modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Text(
            text = overview,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun MovieImage(movie: MovieDetails, modifier: Modifier = Modifier){
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreGrid(genres: List<Genre>, modifier: Modifier = Modifier){
    Box(modifier = modifier.heightIn(max = 200.dp)) {
        FlowRow {
            for(genre in genres){
                GenreCard(genre)
            }
        }
    }
}

@Composable
fun GenreCard(genre: Genre,modifier: Modifier = Modifier){
    val rnd = Random()
    val color = Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    Card(
        modifier = modifier
            .padding(5.dp)
            .wrapContentSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp,color),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = genre.name,
            maxLines = 1,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview
@Composable
fun GenreGridPreview(){
    val rnd: Random = Random()
    val mockData = List(10) {Genre("$it".toInt(),"Test_${rnd.nextInt(256)}")}
    GenreGrid(genres = mockData)
}

@Preview
@Composable
fun GenreCardPreview(){
    GenreCard(genre = Genre(1,"History"))
}