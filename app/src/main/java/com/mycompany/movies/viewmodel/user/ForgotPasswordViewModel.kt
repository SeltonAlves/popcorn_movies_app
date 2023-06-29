package com.mycompany.movies.viewmodel.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.mycompany.movies.model.ValidationModel


class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val _response = MutableLiveData<ValidationModel>()
    val response: LiveData<ValidationModel> = _response

    fun recuperationLogin(email: String) {
        if (email != "") {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    _response.value = ValidationModel()
                }
            }.addOnFailureListener {
                val message = when (it) {
                    is FirebaseAuthUserCollisionException -> "Usuário Já cadastrado."
                    is FirebaseTooManyRequestsException -> "Requisição bloqueada por Movitivo de Atividade Suspeita, " +
                            "Verifique sua Caixa De Email."
                    is FirebaseNetworkException -> "Erro de Conexão verifique se está ligado o WIFI ou Dados Móveis"
                    else -> "entre em Contatos Com os Desenvolvedores."
                }

                _response.value = ValidationModel(message)
            }
        } else {
            _response.value =
                ValidationModel("Email não Foi Informado.")
        }
    }


}