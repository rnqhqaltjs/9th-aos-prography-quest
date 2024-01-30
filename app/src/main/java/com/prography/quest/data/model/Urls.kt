package com.prography.quest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Urls(
    @field:Json(name = "full")
    val full: String,
    @field:Json(name = "raw")
    val raw: String,
    @field:Json(name = "regular")
    val regular: String,
    @field:Json(name = "small")
    val small: String,
    @field:Json(name = "thumb")
    val thumb: String
)