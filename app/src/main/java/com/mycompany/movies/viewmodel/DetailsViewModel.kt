package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.model.DetailedMovies
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository()

    private var _movies = MutableLiveData<DetailedMovies>()
    val movies: LiveData<DetailedMovies> = _movies
    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    fun getIdMovies(code: Int?) {
        if (code != null) {
            repository.getIdMovies(code, object : ApiResponse<DetailedMovies> {
                override fun success(result: DetailedMovies) {
                    _movies.value = result
                }

                override fun failure(message: String) {
                    _error.value = message
                }

            })
        }
    }

}