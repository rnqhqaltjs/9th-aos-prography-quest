package com.prography.quest.data.repository

import com.prography.quest.data.api.RandomPhotoService
import com.prography.quest.data.model.PhotosResponseItem
import retrofit2.Response
import javax.inject.Inject

class RandomPhotoRepositoryImpl @Inject constructor(
    private val randomPhotoService: RandomPhotoService
) : RandomPhotoRepository {

    override suspend fun getRandomPhoto(
        count: Int
    ): Response<List<PhotosResponseItem>> {
        return randomPhotoService.getRandomPhoto(count)
    }
}
