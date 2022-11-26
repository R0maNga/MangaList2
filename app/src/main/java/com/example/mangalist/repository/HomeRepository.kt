package com.example.mangalist.repository

import com.example.mangalist.api_service.ApiService
import com.example.mangalist.model.MangaData

class HomeRepository(
    private val apiService: ApiService
) {
    suspend fun getManga(rand:Int): MangaData {
        return apiService.getMangaList(rand)
    }


}