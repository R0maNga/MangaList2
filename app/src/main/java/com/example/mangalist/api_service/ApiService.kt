package com.example.mangalist.api_service

import com.example.mangalist.model.Manga
import com.example.mangalist.model.MangaData
import com.example.mangalist.model.MangaDataForId
import com.example.mangalist.test.TestModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("manga")
    suspend fun getMangaList(
        @Query("page")p:Int
    ): MangaData

    @GET("manga/{id}")
    suspend fun getMangaById(
        @Path("id") id: Int
    ): MangaDataForId
    @GET("manga")
    suspend fun getMangaByName(
        @Query("letter") name:String,
        @Query("limit")limit:Int
    ): MangaData
    @GET("mangas")
    suspend fun getTestImage(): TestModel
}