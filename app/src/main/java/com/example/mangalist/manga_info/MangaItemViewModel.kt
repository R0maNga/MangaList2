package com.example.mangalist.manga_info

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangalist.api_service.ApiService
import com.example.mangalist.model.MangaDataForId
import com.example.mangalist.repository.MangaItemRepository
import com.example.mangalist.retrofit.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MangaItemViewModel:ViewModel() {
    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val apiService = retrofit.create(ApiService::class.java)
    private val mangaItemRepository = MangaItemRepository(apiService)

    var liveMangaData: MutableLiveData<MangaDataForId> = MutableLiveData()

    fun getMangaById(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mangas = mangaItemRepository.getMangaById(id)
                liveMangaData.postValue(mangas)
            }
            catch (e:Exception )
            {
                Log.e("rty",e.localizedMessage)
            }


        }
    }
    var liveMangaDataStatus: MutableLiveData<Boolean> = MutableLiveData()
    fun processingData(id: String){
        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference
        var state = false
        viewModelScope.launch(Dispatchers.IO) {

            database.child(auth.currentUser?.email.toString().substringBefore("@"))
                .get()
                .addOnSuccessListener {
                    it.children.forEach { data ->
                        if (data.key.toString() == id) {
                            state = true
                        }
                    }
                    liveMangaDataStatus.postValue(state)
                }
        }

    }
}