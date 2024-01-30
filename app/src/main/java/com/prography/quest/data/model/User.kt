package com.prography.quest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name = "bio")
    val bio: String? = null,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "instagram_username")
    val instagramUsername: String? = null,
    @field:Json(name = "links")
    val links: LinksX,
    @field:Json(name = "location")
    val location: String? = null,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "portfolio_url")
    val portfolioUrl: String? = null,
    @field:Json(name = "profile_image")
    val profileImage: ProfileImage,
    @field:Json(name = "total_collections")
    val totalCollections: Int,
    @field:Json(name = "total_likes")
    val totalLikes: Int,
    @field:Json(name = "total_photos")
    val totalPhotos: Int,
    @field:Json(name = "twitter_username")
    val twitterUsername: String? = null,
    @field:Json(name = "username")
    val username: String
)