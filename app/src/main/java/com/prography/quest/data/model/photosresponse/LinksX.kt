package com.prography.quest.data.model.photosresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    @field:Json(name = "html")
    val html: String,
    @field:Json(name = "likes")
    val likes: String,
    @field:Json(name = "photos")
    val photos: String,
    @field:Json(name = "portfolio")
    val portfolio: String,
    @field:Json(name = "self")
    val self: String
)