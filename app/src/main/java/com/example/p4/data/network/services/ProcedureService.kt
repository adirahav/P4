package com.example.p4.data.network.services

import com.example.p4.common.Configuration.API_BASE_URL
import com.example.p4.data.network.model.ProcedureModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class ProcedureService private constructor() {
    val procedureAPI: ProcedureAPI

    companion object {
        var instance: ProcedureService? = null
            get() {
                if (field == null) {
                    field = ProcedureService()
                }
                return field
            }
            private set
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API_BASE_URL).build()
        procedureAPI = retrofit.create(ProcedureAPI::class.java)
    }

    interface ProcedureAPI {
        @GET("/procedure/get{procedure_id}")
        fun getProcedure(
                @Path("procedure_id") procedureID: Int
        ): Call<ProcedureModel?>?
    }
}