package com.prography.quest.data.repository

import com.prography.quest.data.remote.PhotoService
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import retrofit2.Response
import javax.inject.Inject

class RandomPhotoRepositoryImpl @Inject constructor(
    private val randomPhotoService: PhotoService
) : RandomPhotoRepository {

    override suspend fun getRandomPhoto(
        count: Int
    ): Response<List<PhotosResponseItem>> {
        return randomPhotoService.getRandomPhoto(count)
    }
}
