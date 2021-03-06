package com.example.rxjavaretrofit.serviceBuilder

import com.example.rxjavaretrofit.endPoints.TmdbEndpoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TmdbServiceBuilder {

    private val loggingTmbd = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(loggingTmbd)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TmdbEndpoints::class.java)

//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.themoviedb.org/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(client)
//        .build()

//    fun<T> buildService(service: Class<T>): T{
//        return retrofit.create(service)
//    }

    fun buildService(): TmdbEndpoints {
        return retrofit
    }
}