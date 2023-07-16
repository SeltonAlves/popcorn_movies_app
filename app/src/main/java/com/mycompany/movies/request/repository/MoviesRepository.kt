package com.mycompany.movies.request.repository


import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.google.gson.Gson
import com.mycompany.movies.model.ErrorResponse
import com.mycompany.movies.model.FuturesMovies
import com.mycompany.movies.model.Movies
import com.mycompany.movies.model.Result
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
                                    listener.failure("erro, não foi possível carregar as imagens.")
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
                                    listener.failure("erro, não foi possível carregar as imagens.")
                                }
                            }
                        }
                    } else {
                        listener.failure("As Buscas pelo conteúdo foram mal sucessidas.")
                    }
                }
            }

            override fun onFailure(call: Call<FuturesMovies>, t: Throwable) {
                if (t is IOException) {
                    listener.failure("erro, sem conexão!")
                } else {
                    listener.failure("erro estamos verificando, breve estará no ar.")
                }
            }

        })
    }

    fun getFutureMovies(listener: ApiResponse<List<Pair<String, Bitmap?>>>) {
        val call = remote.getFutureMovies()
        call.enqueue(object : Callback<FuturesMovies> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<FuturesMovies>, response: Response<FuturesMovies>) {
                if (response.isSuccessful) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        response.body()?.let { movies ->
                            CoroutineScope(Dispatchers.Main).launch {
                                val moviesFuture =
                                    repositoryImage.getCurrentlyInMovies(movies.results)
                                if (moviesFuture.isNotEmpty()) {
                                    listener.success(moviesFuture)
                                } else {
                                    listener.failure("erro, não foi possível carregar as imagens.")
                                }
                            }
                        }
                    }
                } else {
                    listener.failure("As Buscas pelo conteúdo foram mal sucessidas.")
                }
            }

            override fun onFailure(call: Call<FuturesMovies>, t: Throwable) {
                if (t is IOException) {
                    listener.failure("erro, sem conexão!")
                } else {
                    listener.failure("erro estamos verificando, breve estará no ar.")
                }
            }

        })
    }

    fun searchMovies(movies: String, listener: ApiResponse<List<Result>>) {
        val call = remote.searchMovies(movies)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    val list = mutableListOf<Result>()
                    response.body()?.let { movies ->
                        for (results in movies.results){
                            list.add(results)
                        }
                    }
                    listener.success(list)
                } else {
                    listener.failure("erro")
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                listener.failure("erro 2")
            }

        })
    }
}
