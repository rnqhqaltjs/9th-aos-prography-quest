package com.prography.quest.data.api

import com.prography.quest.data.model.PhotosResponseItem
import com.prography.quest.util.Constants.CLIENT_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosService {
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): Response<List<PhotosResponseItem>>
}