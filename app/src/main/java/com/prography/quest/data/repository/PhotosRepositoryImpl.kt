package com.prography.quest.data.repository

import com.prography.quest.data.api.PhotosService
import com.prography.quest.data.model.PhotosResponseItem
import retrofit2.Response
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val photosService: PhotosService
) : PhotosRepository {

    override suspend fun getPhotos(
        page: Int,
        per_page: Int,
        order_by: String
    ): Response<List<PhotosResponseItem>> {
        return photosService.getPhotos(page, per_page, order_by)
    }

}