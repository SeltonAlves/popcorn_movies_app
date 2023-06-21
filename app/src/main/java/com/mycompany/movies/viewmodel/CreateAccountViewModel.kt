package com.mycompany.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    fun createAccount(email: String, password: String, confirmPassword: String) {
        if (email != "" && password == confirmPassword) {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                _status.value = true
            }.addOnFailureListener {
                _status.value = false
            }
        } else if (password != confirmPassword) {
            _status.value = false
        } else {
            _status.value = false
        }
    }

}