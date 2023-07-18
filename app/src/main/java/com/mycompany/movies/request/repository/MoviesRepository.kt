package com.mycompany.movies.request.repository


import com.google.gson.Gson
import com.mycompany.movies.model.ErrorResponse
import com.mycompany.movies.model.FuturesMovies
import com.mycompany.movies.model.Movies
import com.mycompany.movies.model.Result
import com.mycompany.movies.request.Retrofit
import com.mycompany.movies.request.service.ApiResponse
import com.mycompany.movies.request.service.MoviesServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection


class MoviesRepository {

    private val remote = Retrofit.getService(MoviesServices::class.java)
    private lateinit var call: Call<FuturesMovies>
    fun getMoviesPopular(listener: ApiResponse<List<String>>) {
        val call = remote.getPopularMovies()
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        response.body()?.let { movies ->
                            val list = mutableListOf<String>()
                            movies.results.map {
                                list.add(it.poster_path)
                            }
                            listener.success(list)
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

    fun getInMovies(code: Int, listener: ApiResponse<List<Pair<String, String?>>>) {
        call = if (code == 1) {
            remote.geCurrentlyInMovies()
        }else{
            remote.getFutureInMovies()
        }
        call.enqueue(object : Callback<FuturesMovies> {
            override fun onResponse(call: Call<FuturesMovies>, response: Response<FuturesMovies>) {
                if (response.isSuccessful) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        response.body()?.let {
                            val list: MutableList<Pair<String, String?>> = mutableListOf()
                            for (results in it.results) {
                                list.add(Pair(results.title, results.poster_path))
                            }
                            listener.success(list)
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
