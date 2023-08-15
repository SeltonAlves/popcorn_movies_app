package com.mycompany.movies.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.mycompany.movies.model.DetailedMovies
import com.mycompany.movies.model.InfomationMovies
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse
import com.mycompany.movies.util.Constraints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat

class DetailsViewModel(private val application: Application) : AndroidViewModel(application) {
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

    @SuppressLint("SuspiciousIndentation")
    fun getMovies(
        result: (
            details: InfomationMovies
        ) -> Unit,
    ) {
        var poster: Bitmap? = null
        var backdrop: Bitmap? = null


        runBlocking {
            launch(Dispatchers.IO) {
                poster = Glide.with(application).asBitmap()
                    .load(
                        Constraints.URL_BASE_IMG + _movies.value?.poster_path
                    ).submit().get()

                backdrop = Glide.with(application).asBitmap()
                    .load(
                        Constraints.URL_BASE_IMG + _movies.value?.backdrop_path
                    ).submit().get()
            }
        }

        val list = mutableListOf<Int>()

        _movies.value?.production_companies?.map {
            list.add(it.id)
        }

        val format = DecimalFormat("#.##")

        result.invoke(
            InfomationMovies(
                nameMovies = _movies.value?.title,
                posterMovies = poster,
                backdrop = backdrop,
                aboutMovies = _movies.value?.overview!!,
                voteAverage = format.format(_movies.value!!.vote_average),
                productionCompanies = list
            )
        )

    }

}