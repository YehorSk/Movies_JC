package com.example.movies_jc.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies_jc.ui.screens.HomeScreen
import com.example.movies_jc.ui.screens.MoviesViewModel
import com.example.movies_jc.R

@Composable
fun MoviesApp(){
    Scaffold(
        topBar = { MoviesAppTopBar() },
    ) { contentPadding ->
        val moviesViewModel : MoviesViewModel = viewModel(factory = MoviesViewModel.Factory)
        HomeScreen(
            moviesUiState = moviesViewModel.moviesUiState,
            moviesViewModel = moviesViewModel,
            retryAction = moviesViewModel::getMovies,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesAppTopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.top_bar_default_title)
            )
        }
    )
}