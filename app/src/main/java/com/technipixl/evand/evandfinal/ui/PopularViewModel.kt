package com.technipixl.evand.evandfinal.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.technipixl.evand.evandfinal.Network.PopularAPIServiceImpl
import kotlinx.coroutines.launch

class PopularViewModel: BaseViewModel() {

    override val service = PopularAPIServiceImpl()
    fun getDatas(){
        viewModelScope.launch {
            getMovies()
        }
    }

    override suspend fun getMovies() {
        val result = service.getTrendingMovies(service.APIKEY, 1)
        movies.emit(result)
    }
}