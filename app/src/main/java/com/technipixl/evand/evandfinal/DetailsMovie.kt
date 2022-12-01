package com.technipixl.evand.evandfinal

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import com.technipixl.evand.evandfinal.Network.BaseApiService
import com.technipixl.evand.evandfinal.model.MovieResult
import com.technipixl.evand.evandfinal.ui.DetailViewModel
import com.technipixl.evand.evandfinal.ui.theme.EvandFinalTheme

@Composable
fun DetailsMovie(
    modifier: Modifier = Modifier,
    onClick: (MovieResult.Movie) -> Unit,
    movie: MovieResult.Movie,
    viewModel: DetailViewModel =viewModel()
) {
    val scrollTexts = rememberScrollState()
    viewModel.currentMovie = movie

    Box(modifier = modifier) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${viewModel.currentMovie.backdropPath}"),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.30f)
                .offset(y = (-48).dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.10f))
            with(viewModel.currentMovie) {
                TitleRow(title = title, rating = voteAverage, posterPath = posterPath)
            }
            Text(text = "Synopsis", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.40f)
                    .verticalScroll(scrollTexts),
                text = viewModel.currentMovie.overview ?: ""
            )
            Text(
                fontWeight = FontWeight.Bold,
                text = "Similar titles"
            )

        }
    }
}

@Composable
fun TitleRow(
    modifier: Modifier = Modifier,
    title: String?,
    rating: Double?,
    posterPath: String?
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Card(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .border(3.dp, Color.White, shape = RoundedCornerShape(20)),

            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${posterPath}"),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
            title?.let {
                Text(
                    it,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(15.dp),
                    fontSize = 20.sp
                )
            }
        }
        Box(contentAlignment = Alignment.TopEnd){
            RatingCircle(
                rating = rating,
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = 55.dp),
                textSize = 25.sp,
            )
        }
    }
}

@Composable
fun DetailBackHeader(onClick: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

