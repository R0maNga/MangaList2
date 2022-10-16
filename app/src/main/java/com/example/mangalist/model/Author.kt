package com.example.mangalist.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("mal_id")
    val id: Int,
    val name: String
)