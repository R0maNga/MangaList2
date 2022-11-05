package com.example.mangalist.model

import com.google.gson.annotations.SerializedName

data class DbManga(
    var id: String,
    var images: String?,
    var title: String?,
    var rank: String?,
    var watchStatus: String?
)
