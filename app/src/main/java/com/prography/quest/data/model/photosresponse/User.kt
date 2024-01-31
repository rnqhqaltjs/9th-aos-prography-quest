package com.prography.quest.data.model.photosresponse


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name = "bio")
    val bio: String?,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "instagram_username")
    val instagramUsername: String?,
    @field:Json(name = "links")
    val links: LinksX,
    @field:Json(name = "location")
    val location: String?,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "portfolio_url")
    val portfolioUrl: String?,
    @field:Json(name = "profile_image")
    val profileImage: ProfileImage,
    @field:Json(name = "total_collections")
    val totalCollections: Int,
    @field:Json(name = "total_likes")
    val totalLikes: Int,
    @field:Json(name = "total_photos")
    val totalPhotos: Int,
    @field:Json(name = "twitter_username")
    val twitterUsername: String?,
    @field:Json(name = "username")
    val username: String
)