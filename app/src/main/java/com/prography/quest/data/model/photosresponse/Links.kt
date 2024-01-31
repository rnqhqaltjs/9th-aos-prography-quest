package com.prography.quest.data.model.photosresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @field:Json(name = "download")
    val download: String,
    @field:Json(name = "download_location")
    val downloadLocation: String,
    @field:Json(name = "html")
    val html: String,
    @field:Json(name = "self")
    val self: String
)