package com.example.mangalist.model

import com.google.gson.annotations.SerializedName

data class Manga(
    @SerializedName("mal_id")
    var id: Int,
    var images: Image?,
    var title: String?,
    @SerializedName("title_japanese")
    var titleJapanese: String?,
    var type: String?,
    var chapters: Int?,
    var volumes: Int?,
    var status: String?,
    var score: Double?,
    var rank: Int?,
    var popularity: Int?,
    var synopsis: String?,
    var authors: List<Author>,
    var genres: List<Genre>
)
