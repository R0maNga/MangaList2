package com.example.mangalist.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangalist.api_service.ApiService
import com.example.mangalist.model.MangaData
import com.example.mangalist.repository.HomeRepository
import com.example.mangalist.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val apiService = retrofit.create(ApiService::class.java)
    private val homeRepository = HomeRepository(apiService)

    val progressBarLiveData = MutableLiveData<Boolean>(false)
    var liveMangaData: MutableLiveData<MangaData> = MutableLiveData()

    fun getManga() {
        val rand = (1..20).random()
        progressBarLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val mangas = homeRepository.getManga(rand)
            progressBarLiveData.postValue(false)
            liveMangaData.postValue(mangas)
        }
    }

}