package com.prography.quest.data.model.photosresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosResponseItem(
    @field:Json(name = "blur_hash")
    val blurHash: String?,
    @field:Json(name = "color")
    val color: String,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "current_user_collections")
    val currentUserCollections: List<CurrentUserCollection>,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "liked_by_user")
    val likedByUser: Boolean,
    @field:Json(name = "likes")
    val likes: Int,
    @field:Json(name = "links")
    val links: Links,
    @field:Json(name = "updated_at")
    val updatedAt: String,
    @field:Json(name = "urls")
    val urls: Urls,
    @field:Json(name = "user")
    val user: User,
    @field:Json(name = "width")
    val width: Int
)