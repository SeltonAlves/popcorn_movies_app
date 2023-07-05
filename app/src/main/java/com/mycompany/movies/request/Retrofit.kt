package com.mycompany.movies.request

import com.mycompany.movies.util.Constrants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    private val instance: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constrants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    fun <T> getService(service: Class<T>): T {
        return instance.create(service)
    }
}

object RetrofitImage {
    private val instance: Retrofit by lazy {
            Retrofit.Builder().baseUrl(Constrants.URL_BASE_IMG)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun <T> getService(service: Class<T>): T {
            return instance.create(service)
        }
    }