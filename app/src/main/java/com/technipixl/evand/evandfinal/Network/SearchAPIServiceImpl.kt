package com.technipixl.evand.evandfinal.Network

import com.technipixl.evand.evandfinal.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

class SearchAPIServiceImpl: BaseApiService() {
    interface SearchMovieRoutes{
        @GET("search/movie")
        suspend fun searchResult(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int,
            @Query("query") query: String
        ) : MovieResult
    }

    suspend fun getMoviesResults(
        apiKey: String,
        page: Int,
        query: String,
        language: String = "fr")
            = retrofit.create(SearchMovieRoutes::class.java)
        .searchResult(apiKey, language, page, query).results
}