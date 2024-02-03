package com.prography.quest.data.remote

import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.util.Constants.CLIENT_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): Response<List<PhotosResponseItem>>

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos/{id}")
    suspend fun getPhotoDetails(@Path("id") id: String): Response<PhotosResponseItem>

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos/random")
    suspend fun getRandomPhoto(
        @Query("count") count: Int
    ): Response<List<PhotosResponseItem>>
}