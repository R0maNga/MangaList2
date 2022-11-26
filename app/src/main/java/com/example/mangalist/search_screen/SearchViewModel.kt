package com.example.mangalist.search_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangalist.api_service.ApiService
import com.example.mangalist.model.MangaData
import com.example.mangalist.repository.HomeRepository
import com.example.mangalist.repository.SearchItemRepository
import com.example.mangalist.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel(){
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()}

    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val apiService = retrofit.create(ApiService::class.java)
    private val searchItemRepository = SearchItemRepository(apiService)

    var liveMangaData: MutableLiveData<MangaData> = MutableLiveData()

    fun getMangaByName(name:String) {
        val rand = 20
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val mangas = searchItemRepository.getMangaByName(name,rand)
                liveMangaData.postValue(mangas)
            }
            catch (e:Exception)
            {

            }

        }
    }}
