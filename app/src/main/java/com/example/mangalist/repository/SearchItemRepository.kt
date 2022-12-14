package com.example.mangalist.repository

import android.util.Log
import com.example.mangalist.api_service.ApiService
import com.example.mangalist.model.MangaData
import com.example.mangalist.model.MangaDataForId

class SearchItemRepository(
    private val apiService: ApiService
) {
    suspend fun getMangaByName(name: String, limit: Int): MangaData {
        val nnn = apiService.getMangaByName(name,limit)
        Log.e("MangA", nnn.toString())
        return nnn
    }
}