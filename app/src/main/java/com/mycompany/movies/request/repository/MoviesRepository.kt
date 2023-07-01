package com.mycompany.movies.request.repository


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mycompany.movies.model.ErrorResponse
import com.mycompany.movies.model.Movies
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

    fun getMoviesPopular(listener:ApiResponse<Movies>) {
        val call = remote.getPopularMovies()
        call.enqueue(object :Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    if (response.code() == HttpURLConnection.HTTP_OK){
                        response.body()?.let {
                            listener.success(it)
                        }
                    }else if(response.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
                        val error = Gson().fromJson(response.errorBody()?.toString(),ErrorResponse::class.java)
                        listener.failure(error.status_message)
                    }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                if (t is IOException){
                    listener.failure("erro, sem conexão!")
                }else{
                    listener.failure("erro estamos verificando, breve estará no ar.")
                }
            }

        })

    }


}