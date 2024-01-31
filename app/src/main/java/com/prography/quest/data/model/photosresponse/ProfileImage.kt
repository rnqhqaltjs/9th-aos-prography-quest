package com.prography.quest.data.model.photosresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileImage(
    @field:Json(name = "large")
    val large: String,
    @field:Json(name = "medium")
    val medium: String,
    @field:Json(name = "small")
    val small: String
)