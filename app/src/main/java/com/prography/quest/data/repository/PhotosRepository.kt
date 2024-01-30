package com.prography.quest.data.repository

import com.prography.quest.data.model.PhotosResponseItem
import retrofit2.Response

interface PhotosRepository {

    suspend fun getPhotos(
        page: Int,
        per_page: Int,
        order_by: String
    ): Response<List<PhotosResponseItem>>
}