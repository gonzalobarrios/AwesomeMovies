package com.barriosartola.awesomeapp.presentation.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barriosartola.awesomeapp.data.controller.AuthController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(private val authController: AuthController) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isDataValid: LiveData<Boolean> =
        MediatorLiveData<Boolean>().apply {
            addSource(email) { postValue(checkDataValid()) }
            addSource(password) { postValue(checkDataValid()) }
        }

    val isLoading: LiveData<Boolean>
        get() = localIsLoading
    val error: LiveData<Throwable>
        get() = localError
    val loginSuccess: LiveData<Boolean>
        get() = localLoginSuccess

    private val job = Job()

    private val localLoginSuccess = MutableLiveData<Boolean>()
    private val localError = MutableLiveData<Throwable>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun login() {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                authController.login(
                        email.value ?: "",
                password.value ?: ""
                )
                localLoginSuccess.postValue(true)
            } catch (exception: Throwable) {
                localError.postValue(exception)
                localLoginSuccess.postValue(false)
                localIsLoading.postValue(false)
            }
        }
    }

    private fun checkDataValid() =
        !email.value.isNullOrBlank()
                && !password.value.isNullOrBlank()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
