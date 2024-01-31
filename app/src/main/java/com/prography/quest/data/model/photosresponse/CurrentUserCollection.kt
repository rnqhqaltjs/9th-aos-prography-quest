package com.prography.quest.data.model.photosresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentUserCollection(
    @field:Json(name = "cover_photo")
    val coverPhoto: Any,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "last_collected_at")
    val lastCollectedAt: String,
    @field:Json(name = "published_at")
    val publishedAt: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "updated_at")
    val updatedAt: String,
    @field:Json(name = "user")
    val user: Any
)