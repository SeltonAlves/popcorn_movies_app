package com.mycompany.movies.request.service

import com.mycompany.movies.model.DetailedMovies
import com.mycompany.movies.model.FuturesMovies
import com.mycompany.movies.model.Movies
import com.mycompany.movies.util.Constraints
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {
    @GET(Constraints.POPULAR_MOVIES)
    fun getPopularMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constraints.API_KEY
    ): Call<Movies>

    @GET(Constraints.URL_FUTURE_IN_MOVIES)
    fun getFutureInMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constraints.API_KEY
    ): Call<FuturesMovies>

    @GET(Constraints.URL_CURRENTLY_IN_MOVIES)
    fun geCurrentlyInMovies(
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constraints.API_KEY
    ): Call<FuturesMovies>

    @GET(Constraints.URL_SEARCH_MOVIES)
    fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1,
        @Header("accept") accept: String = "application/json",
        @Query("api_key") apiKey: String = Constraints.API_KEY
    ): Call<Movies>

    @GET(Constraints.URL_GET_ID_MOVIES)
    fun getIdMovies(@Path("id") id: Int): Call<DetailedMovies>
}