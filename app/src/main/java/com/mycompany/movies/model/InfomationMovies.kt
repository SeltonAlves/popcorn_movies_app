package com.mycompany.movies.model

import android.graphics.Bitmap
data class InfomationMovies(
    val nameMovies: String? =null,
    val posterMovies: Bitmap? = null,
    val backdrop: Bitmap? = null,
    val voteAverage: String,
    val aboutMovies: String,
    val productionCompanies: List<Int>
)
