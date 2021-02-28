package com.example.p4.data.network.services

import com.example.p4.common.Configuration.API_BASE_URL
import com.example.p4.data.network.model.SplashModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class SplashService private constructor() {
    val splashAPI: SplashAPI

    companion object {
        var instance: SplashService? = null
            get() {
                if (field == null) {
                    field = SplashService()
                }
                return field
            }
            private set
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API_BASE_URL).build()
        splashAPI = retrofit.create(SplashAPI::class.java)
    }

    interface SplashAPI {
        @get:GET("/splash/data")
        val splash: Call<SplashModel?>?
    }


}