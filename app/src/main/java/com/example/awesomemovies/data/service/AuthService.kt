package com.barriosartola.awesomeapp.data.service

import com.barriosartola.awesomeapp.data.service.request.LoginRequest
import com.barriosartola.awesomeapp.data.service.response.SuccessReponse
import com.barriosartola.awesomeapp.data.service.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): TokenResponse

    @POST("auth/logout")
    suspend fun logout(): SuccessReponse
}
