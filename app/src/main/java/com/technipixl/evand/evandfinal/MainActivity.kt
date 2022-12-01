package com.technipixl.evand.evandfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.technipixl.evand.evandfinal.model.MovieResult
import com.technipixl.evand.evandfinal.ui.theme.EvandFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvandFinalTheme() {
                MovieApp()
            }
        }
    }
}

@Composable
fun Header(currentScreen: NavRoutes, backOnClick: () -> Unit){
    when(currentScreen){
        NavRoutes.PopularMovie -> PopularHeader()
        NavRoutes.DetailsMovie -> DetailBackHeader(backOnClick)
    }
}

@Composable
fun MovieApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: NavRoutes.SearchMovie.name
    var screen by remember {
        mutableStateOf(NavRoutes.SearchMovie)
    }

    Scaffold(
        topBar = {
                 Header(screen) {
                     navController.popBackStack()
                 }
        },
        bottomBar = {
            BottomNavigation(
                selected = currentScreen,
                navigationChanged = { navController.navigate(it) }
            )
        }) { innerPadding ->

        var selectedMovie by remember { mutableStateOf<MovieResult.Movie?>(null) }

        NavHost(
            navController = navController,
            startDestination = NavRoutes.SearchMovie.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = NavRoutes.SearchMovie.name) {
                SearchMovie()
                screen = NavRoutes.SearchMovie
            }

            composable(route = NavRoutes.PopularMovie.name) {

                PopularMovie(onClick = { movie ->
                    selectedMovie = movie
                    navController.navigate(NavRoutes.DetailsMovie.name) {
                        popUpTo(NavRoutes.DetailsMovie.name) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
                screen = NavRoutes.PopularMovie
            }

            composable(route = NavRoutes.DetailsMovie.name) {
                DetailsMovie(onClick = {  movie ->
                    selectedMovie = movie
                    navController.navigate(NavRoutes.DetailsMovie.name) {
                        popUpTo(NavRoutes.DetailsMovie.name) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                    movie = selectedMovie!!)
                screen = NavRoutes.DetailsMovie
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EvandFinalTheme {
        MovieApp()
    }
}