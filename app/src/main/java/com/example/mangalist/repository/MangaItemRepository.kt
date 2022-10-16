package com.example.mangalist.repository

import android.util.Log
import com.example.mangalist.api_service.ApiService
import com.example.mangalist.model.Manga
import com.example.mangalist.model.MangaData
import com.example.mangalist.model.MangaDataForId

class MangaItemRepository (
    private val apiService: ApiService
) {
    suspend fun getMangaById(id: Int): MangaDataForId {
        val nnn = apiService.getMangaById(id)
        Log.e("MangA", nnn.toString())
        return nnn
    }

}