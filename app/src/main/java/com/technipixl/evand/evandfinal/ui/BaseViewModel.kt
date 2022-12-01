package com.technipixl.evand.evandfinal.ui

import androidx.lifecycle.ViewModel
import com.technipixl.evand.evandfinal.Network.BaseApiService
import com.technipixl.evand.evandfinal.model.MovieResult
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModel: ViewModel() {
    abstract val service: BaseApiService
    var movies = MutableSharedFlow<List<MovieResult.Movie>>()
    abstract suspend fun getMovies()
}