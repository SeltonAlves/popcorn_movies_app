package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.model.Details
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class FutureMoviesFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository()

    private var _movies = MutableLiveData<List<Details>>()
    val movies: LiveData<List<Details>> = _movies

    private var _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _isLoading

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getFutureMovies() {
        repository.getInMovies(2, object :ApiResponse<List<Details>>{
            override fun success(result: List<Details>) {
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