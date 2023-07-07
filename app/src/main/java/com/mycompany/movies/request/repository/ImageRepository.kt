package com.mycompany.movies.request.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.mycompany.movies.model.Result
import com.mycompany.movies.request.RetrofitImage
import com.mycompany.movies.request.service.ImageServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.io.IOException

class ImageRepository {
    private val remote = RetrofitImage.getService(ImageServices::class.java)

    suspend fun getImgCatalog(list: List<Result>): List<Bitmap?> {
        return withContext(Dispatchers.IO) {
            val listMovies = mutableListOf<Bitmap?>()
            try {
                for (movie in list) {
                    val response = remote.getImage(movie.poster_path).awaitResponse()
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val inputStream = responseBody?.byteStream()
                        if (inputStream != null) {
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            listMovies.add(bitmap)
                        } else {
                            listMovies.add(null)
                        }
                    } else {
                        listMovies.add(null)
                    }
                }
            } catch (e: IOException) {
                for (movie in list) {
                    listMovies.add(null)
                }
            }
            return@withContext listMovies
        }
    }

    suspend fun getCurrentlyInMovies(list: List<Result>): List<Pair<String, Bitmap?>> {
        return withContext(Dispatchers.IO) {
            val movies: MutableList<Pair<String, Bitmap?>> = mutableListOf()
            for (results in list) {
                val response = remote.getImage(results.poster_path).awaitResponse()
                if (response.isSuccessful) {
                    val body = response.body()
                    val input = body?.byteStream()
                    if (input != null) {
                        val bitmap = BitmapFactory.decodeStream(input)
                        movies.add(Pair(results.title, bitmap))
                    }else{
                        movies.add(Pair(results.title, null))
                    }
                }else{
                    movies.add(Pair(results.title, null))
                }
            }
           return@withContext movies
        }
    }

}
