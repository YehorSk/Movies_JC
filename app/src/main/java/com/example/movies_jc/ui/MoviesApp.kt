package com.example.movies_jc.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movies_jc.ui.screens.HomeScreen
import com.example.movies_jc.ui.screens.MoviesViewModel
import com.example.movies_jc.R
import com.example.movies_jc.ui.screens.MovieDetailsScreen

enum class MoviesScreen(@StringRes val title: Int){
    Main(title = R.string.movie_list_screen),
    MovieDetails(title = R.string.movie_details_screen)
}

@Composable
fun MoviesApp(){
    val moviesViewModel : MoviesViewModel = viewModel(factory = MoviesViewModel.Factory)
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MoviesScreen.valueOf(
        backStackEntry?.destination?.route ?: MoviesScreen.Main.name
    )
    Scaffold(
        topBar = { MoviesAppTopBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() }
        ) },
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = MoviesScreen.Main.name,
            modifier = Modifier.padding(contentPadding)
        ){
            composable(route = MoviesScreen.Main.name){
                HomeScreen(
                    moviesUiState = moviesViewModel.moviesUiState,
                    moviesViewModel = moviesViewModel,
                    retryAction = moviesViewModel::getMovies,
                    onNavigateAction = {navController.navigate(MoviesScreen.MovieDetails.name)}
                )
            }
            composable(route = MoviesScreen.MovieDetails.name){
                MovieDetailsScreen(
                    movieDetailsUiState = moviesViewModel.movieDetailsUiState,
                    moviesViewModel = moviesViewModel,
                    retryAction = moviesViewModel::getMovieDetails
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesAppTopBar(
    currentScreen: MoviesScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title)
            )
        },
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}