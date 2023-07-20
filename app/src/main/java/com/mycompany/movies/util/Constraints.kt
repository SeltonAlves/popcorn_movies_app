package com.mycompany.movies.util

class Constraints private constructor(){
    companion object{
        const val URL_BASE : String = "https://api.themoviedb.org/3/"
        const val API_KEY: String = "8a73eae8bab8c9d36ead13c09193098e"
        const val POPULAR_MOVIES :String = "movie/popular?language=pt-BR&page=1"
        const val URL_BASE_IMG: String = "https://image.tmdb.org/t/p/original"
        const val URL_FUTURE_IN_MOVIES:String = "movie/upcoming?language=pt-BR&page=1"
        const val URL_CURRENTLY_IN_MOVIES: String = "movie/now_playing?language=pt-BR&page=1"
        const val URL_SEARCH_MOVIES : String = "search/movie"
        const val URL_GET_ID_MOVIES: String = "movie/{id}?language=pt-BR"
        const val KEY_SEARCH_MOVIES: String = "searchText"
        const val KEY_CODE_MOVIES:String = "code"
    }


}