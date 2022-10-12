package com.example.test_recruitment.retrofit

import com.example.test_recruitment.model.ResponseLogin
import com.example.test_recruitment.model.ResponseOrder
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseLogin>?

    @GET("retrieve_order.php")
    fun getOrder(): Call<ResponseOrder>

}