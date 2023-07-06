package com.mycompany.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
) : Parcelable