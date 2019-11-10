package com.barriosartola.awesomeapp.data.controller

import com.barriosartola.awesomeapp.App
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class RetrofitController(
    private val gsonConverterFactory: GsonConverterFactory
) {
    val accessToken: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODQ5ODBkNmRhZGUyM2I3NzFmNWNhMzE5YWQxN2I2ZCIsInN1YiI6IjVkYmY0OTg0N2QyYmMxMDAxMTM2YmY1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ytD5PPcc50EUnHLrT8F567centRWvUNd5s5vui4dvAk"

    fun initRetrofit() =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()


    private val httpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val response = chain.proceed(chain.request())

                response
            }
            .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer "+accessToken)
                        .build()

                    chain.proceed(request)

            }
            .build()
}
