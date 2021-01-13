package com.encora.encoratestapplication.connectivity.retrofit

import com.encora.encoratestapplication.connectivity.model.ResponseData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AppAPICall {
    @GET("topsongs/limit={limit}/json")
    fun getImages(@Path("limit") limit:Int): Call<ResponseData>
}