package com.example.mangalist.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestRetrofit {

    private var baseUrl = "http://192.168.58.81:5250/api/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}