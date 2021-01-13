package com.encora.encoratestapplication.connectivity.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppAPICallServices {
    private val BASE_URL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/"

    private val api =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppAPICall::class.java)

    fun songApiCall(limit:Int) = api.getImages(limit)
}