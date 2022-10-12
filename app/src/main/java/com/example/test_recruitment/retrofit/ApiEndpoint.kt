package com.example.test_recruitment.retrofit

import com.example.test_recruitment.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("create_mahasiswa.php")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseLogin>?

}