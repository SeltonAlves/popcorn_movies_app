package com.mycompany.movies.request

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> getService(service:Class<T>): T{
        return retrofit.create(service)
    }

}