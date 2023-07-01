package com.mycompany.movies.request.service

interface ApiResponse<T> {

    fun success(result: T)
    fun failure(message: String)
}