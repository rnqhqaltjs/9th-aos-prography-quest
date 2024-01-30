package com.prography.quest.data.repository

import com.prography.quest.data.model.PhotosResponseItem
import retrofit2.Response

interface RandomPhotoRepository {

    suspend fun getRandomPhoto(
        count: Int
    ): Response<List<PhotosResponseItem>>
}