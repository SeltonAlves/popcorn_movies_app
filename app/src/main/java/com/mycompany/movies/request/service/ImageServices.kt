package com.mycompany.movies.request.service

import com.mycompany.movies.util.Constrants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageServices {

    @GET(Constrants.GET_IMAGE)
    fun getImage(@Path("image") img: String):Call<ResponseBody>
}