package com.mycompany.movies.request

import com.mycompany.movies.util.Constraints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val instance: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constraints.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> getService(service: Class<T>): T {
        return instance.create(service)
    }
}
