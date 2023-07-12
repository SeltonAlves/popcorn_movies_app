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
import java.net.SocketTimeoutException

class ImageRepository {
    private val remote = RetrofitImage.getService(ImageServices::class.java)

    suspend fun getImgCatalog(list: List<Result>): List<Bitmap?> {
        return withContext(Dispatchers.IO) {
            val listMovies = mutableListOf<Bitmap?>()
            try {
                list.map { movies ->
                    val response =
                        remote.getImage(movies.poster_path.dropLast(3)).awaitResponse()
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
            try {
                list.map { results ->
                    val response = remote.getImage(results.poster_path.dropLast(3)).awaitResponse()
                    if (response.isSuccessful) {
                        val body = response.body()
                        val input = body?.byteStream()
                        if (input != null) {
                            val bitmap = BitmapFactory.decodeStream(input)
                            movies.add(Pair(results.title, bitmap))
                        } else {
                            movies.add(Pair(results.title, null))
                        }
                    } else {
                        movies.add(Pair(results.title, null))
                    }
                }
            }catch (e : SocketTimeoutException){
                movies.add(Pair("erro",null))
            }

            return@withContext movies
        }
    }

}
