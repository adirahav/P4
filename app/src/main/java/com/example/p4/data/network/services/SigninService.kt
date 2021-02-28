package com.example.p4.data.network.services

import com.example.p4.common.Configuration.API_BASE_URL
import com.example.p4.data.network.model.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class SigninService private constructor() {
    val signinAPI: SigninAPI

    companion object {
        var instance: SigninService? = null
            get() {
                if (field == null) {
                    field = SigninService()
                }
                return field
            }
            private set
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API_BASE_URL).build()
        signinAPI = retrofit.create(SigninAPI::class.java)
    }

    interface SigninAPI {
        @FormUrlEncoded
        @POST("/signin/login")
        fun setLogin(
                @Field("email") email: String?,
                @Field("password") password: String?
        ): Call<UserModel?>?
    }
}