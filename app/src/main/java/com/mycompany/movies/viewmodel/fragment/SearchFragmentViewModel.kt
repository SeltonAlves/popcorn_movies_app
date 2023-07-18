package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.model.Result
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class SearchFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MoviesRepository()

    private var _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private var _movies = MutableLiveData<List<Result>>()
    val movies : LiveData<List<Result>> = _movies

    fun searchMovies(movies : String?){
        if (movies != null) {
            _loading.value = true
            repository.searchMovies(
                movies = movies,
                listener = object : ApiResponse<List<Result>> {
                    override fun success(result: List<Result>) {
                        if (result.isNotEmpty()) {
                            _movies.value = result
                            _loading.value = false
                        }
                    }

                    override fun failure(message: String) {
                        _loading.value = true
                    }

            })
        }
        }
}