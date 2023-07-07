package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class FutureMoviesFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository()

    private val _movies = MutableLiveData<List<Pair<String,Bitmap?>>>()
    val movies : LiveData<List<Pair<String,Bitmap?>>> = _movies

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun currentlyInMovies(){
        repository.getCurrentlyInMovies(object :ApiResponse<List<Pair<String,Bitmap?>>>{
            override fun success(result: List<Pair<String, Bitmap?>>) {
                _isLoading.value = true
                if (result.isNotEmpty()){
                    _movies.value = result
                }
                _isLoading.value = false
            }

            override fun failure(message: String) {
                _isLoading.value = true
                _error.value = message
            }

        })
    }
}