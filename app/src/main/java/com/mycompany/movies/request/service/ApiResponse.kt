package com.mycompany.movies.request.service

import com.mycompany.movies.model.Result
interface ApiResponse<T> {

    fun success(result:T)
    fun failure(message: String)
}