package com.technipixl.evand.evandfinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.technipixl.evand.evandfinal.model.MovieResult
import com.technipixl.evand.evandfinal.ui.PopularViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technipixl.evand.evandfinal.Network.BaseApiService

@Composable
fun PopularMovie(
    modifier: Modifier = Modifier,
    onClick: (MovieResult.Movie) -> Unit,
    viewModel: PopularViewModel = viewModel()
) {
    val baseService: BaseApiService
    val movies by viewModel.movies.collectAsState(initial = listOf())

    viewModel.getDatas()

    if (movies.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(movies) { movie ->
                PopularMovieCell(movie = movie, modifier = Modifier.clickable {
                    onClick(movie)
                })
            }
        }
    }
}

@Composable
fun PopularHeader(modifier: Modifier = Modifier){
    Column(
        modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {}
}

@Composable
fun PopularMovieCell(
    modifier: Modifier = Modifier,
    movie: MovieResult.Movie
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(205.dp)
            .padding(2.dp),
        elevation = 1.dp
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        RatingCircle(
            rating = movie.voteAverage,
            modifier = Modifier.size(35.dp),
            textSize = 16.sp
        )
    }
}

@Composable
fun RatingCircle(modifier: Modifier = Modifier, rating: Double?, textSize: TextUnit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp), horizontalAlignment = Alignment.End) {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(Color.White)
                .border(2.dp, MaterialTheme.colors.primary, shape = CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = String.format("%.1f", rating),
                fontSize = textSize,
                fontWeight = FontWeight.Bold

            )
        }

    }

}