package com.mycompany.movies.model

class ValidationLoginModel(message:String = "") {

    private var status: Boolean = true
    private var validationMessage:String = ""

    init {
        if (message != ""){
            validationMessage = message
            status = false
        }
    }

    fun status() = status
    fun message() = validationMessage

}