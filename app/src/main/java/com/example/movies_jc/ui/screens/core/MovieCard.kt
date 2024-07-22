package com.example.movies_jc.ui.screens.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies_jc.R
import com.example.movies_jc.model.Movie

@Composable
fun MovieCard(photo: Movie, onMovieClick: () -> Unit, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.padding(4.dp),
        onClick = onMovieClick,
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(){
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
}

@Preview
@Composable
fun MovieCardPreview(modifier: Modifier = Modifier){
    MovieCard(photo = Movie(1,"title","overview","poster_path",0.0,"release_date",0.0,1), onMovieClick = { /*TODO*/ })
}