package com.example.test_recruitment.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    const val baseUrl = "http://192.168.1.8/RestApi_Recruitment/"
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : ApiEndpoint {
        return getRetrofit().create(ApiEndpoint::class.java)
    }
}