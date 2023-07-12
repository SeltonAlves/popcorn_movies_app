package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class FutureMoviesFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val remote = MoviesRepository()

    private var _movies = MutableLiveData<List<Pair<String, Bitmap?>>>()
    val movies: LiveData<List<Pair<String, Bitmap?>>> = _movies

    private var _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _isLoading

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getFutureMovies(){
        remote.getFutureMovies(object :ApiResponse<List<Pair<String,Bitmap?>>>{
            override fun success(result: List<Pair<String, Bitmap?>>) {
                _isLoading.value = true
                if (result.size >= 2){
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