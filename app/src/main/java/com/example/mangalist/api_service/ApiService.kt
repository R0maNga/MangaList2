package com.example.mangalist.api_service

import com.example.mangalist.model.Manga
import com.example.mangalist.model.MangaData
import com.example.mangalist.model.MangaDataForId
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("manga")
    suspend fun getMangaList(): MangaData

    @GET("manga/{id}")
    suspend fun getMangaById(
        @Path("id") id: Int
    ): MangaDataForId
    @GET("manga")
    suspend fun getMangaByName(
        @Query("letter") name:String
    ): MangaData

}