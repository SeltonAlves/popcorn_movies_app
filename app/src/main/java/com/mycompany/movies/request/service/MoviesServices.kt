package com.mycompany.movies.request.service

import com.mycompany.movies.model.FuturesMovies
import com.mycompany.movies.model.Movies
import com.mycompany.movies.util.Constrants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {
    @GET(Constrants.POPULAR_MOVIES)
    fun getPopularMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constrants.API_KEY
    ): Call<Movies>

    @GET(Constrants.URL_CURRENTLY_IN_MOVIES)
    fun getCurrentlyInMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constrants.API_KEY
    ): Call<FuturesMovies>

    @GET(Constrants.URL_FUTURE_MOVIES)
    fun getFutureMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constrants.API_KEY
    ): Call<FuturesMovies>

    @GET()
    fun searchMovies(
        @Path("movies") movies : String,
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constrants.API_KEY
    ): Call<Movies>
}