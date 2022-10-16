package com.example.mangalist.model

import com.google.gson.annotations.SerializedName

data class MangaData(
    @SerializedName("data")
    val mangaList: List<Manga>
)