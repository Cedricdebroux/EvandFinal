package com.technipixl.evand.evandfinal.ui

import com.technipixl.evand.evandfinal.Network.BaseApiService
import com.technipixl.evand.evandfinal.Network.SimilarAPIServiceImpl
import com.technipixl.evand.evandfinal.model.MovieResult

class DetailViewModel: BaseViewModel() {

    override val service = SimilarAPIServiceImpl()

    lateinit var currentMovie: MovieResult.Movie

    override suspend fun getMovies() {

    }

}