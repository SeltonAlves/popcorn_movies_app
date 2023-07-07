package com.mycompany.movies.request.repository


import android.graphics.Bitmap
import com.google.gson.Gson
import com.mycompany.movies.model.ErrorResponse
import com.mycompany.movies.model.FuturesMovies
import com.mycompany.movies.model.Movies
import com.mycompany.movies.request.Retrofit
import com.mycompany.movies.request.service.ApiResponse
import com.mycompany.movies.request.service.MoviesServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection


class MoviesRepository {

    private val remote = Retrofit.getService(MoviesServices::class.java)

    private val repositoryImage = ImageRepository()
    fun getMoviesPopular(listener: ApiResponse<List<Bitmap?>>) {
        val call = remote.getPopularMovies()
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        response.body()?.let { movies ->

                            val first10Results = movies.results.take(10)
                            CoroutineScope(Dispatchers.Main).launch {
                                val movieList = repositoryImage.getImgCatalog(first10Results)
                                if (movieList.isNotEmpty()){
                                    listener.success(movieList)
                                }else{
                                    listener.failure("erro")
                                }
                            }
                        }
                    } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        val error = Gson().fromJson(
                            response.errorBody()?.toString(),
                            ErrorResponse::class.java
                        )
                        listener.failure(error.status_message)
                    }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                if (t is IOException) {
                    listener.failure("erro, sem conexão!")
                } else {
                    listener.failure("erro estamos verificando, breve estará no ar.")
                }
            }

        })

    }


    fun getCurrentlyInMovies(listener: ApiResponse<List<Pair<String, Bitmap?>>>) {
        val call = remote.getCurrentlyInMovies()
        call.enqueue(object : Callback<FuturesMovies> {
            override fun onResponse(call: Call<FuturesMovies>, response: Response<FuturesMovies>) {
                if (response.isSuccessful) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        response.body()?.let {
                            CoroutineScope(Dispatchers.Main).launch {
                                val moviesFutures = repositoryImage.getCurrentlyInMovies(it.results)
                                if (moviesFutures.isNotEmpty()) {
                                    listener.success(moviesFutures)
                                } else {
                                    listener.failure("erro")
                                }
                            }
                        }
                    } else {
                        listener.failure("sei lá ")
                    }
                }
            }

            override fun onFailure(call: Call<FuturesMovies>, t: Throwable) {
                listener.failure("errooo lindo ")
            }

        })
    }
}
