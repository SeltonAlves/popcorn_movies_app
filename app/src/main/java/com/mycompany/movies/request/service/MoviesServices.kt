package com.mycompany.movies.request.service

import com.mycompany.movies.model.Movies
import com.mycompany.movies.util.Constrants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesServices {
    @GET(Constrants.POPULAR_MOVIES)
    fun getPopularMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey:String = Constrants.API_KEY
    ): Call<Movies>


}