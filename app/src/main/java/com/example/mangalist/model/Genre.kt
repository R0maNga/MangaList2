package com.example.mangalist.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("mal_id")
    val id: Int,
    val type: String,
    val name: String
)