package com.example.p4.data.network.services

import com.example.p4.common.Configuration.API_BASE_URL
import com.example.p4.data.network.model.FamilyMemberModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class HomeService private constructor() {
    val homeAPI: HomeAPI

    companion object {
        var instance: HomeService? = null
            get() {
                if (field == null) {
                    field = HomeService()
                }
                return field
            }
            private set
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API_BASE_URL).build()
        homeAPI = retrofit.create(HomeAPI::class.java)
    }

    interface HomeAPI {
        @GET("/profile/get/{user_id}")
        fun getFamilyMemebrs(
                @Path("user_id") userID: Int
        ): Call<List<FamilyMemberModel?>?>?
    }
}