package com.mycompany.movies.util

class Constrants private constructor(){
    companion object{
        const val URL_BASE : String = "https://api.themoviedb.org/3/"
        const val API_KEY: String = "8a73eae8bab8c9d36ead13c09193098e"
        const val POPULAR_MOVIES :String = "movie/popular?language=pt-BR&page=1"
    }


}