package com.prography.quest.data.api

import com.prography.quest.data.model.PhotosResponseItem
import com.prography.quest.util.Constants.CLIENT_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RandomPhotoService {
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("photos/random")
    suspend fun getRandomPhoto(
        @Query("count") count: Int
    ): Response<List<PhotosResponseItem>>
}