package com.prography.quest.data.repository

import androidx.paging.PagingData
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PhotosRepository {

    suspend fun getPhotos(
        page: Int,
        per_page: Int,
        order_by: String
    ): Response<List<PhotosResponseItem>>

    suspend fun getPhotoDetails(id: String): Response<PhotosResponseItem>

    fun getPhotosPaging(order_by: String): Flow<PagingData<PhotosResponseItem>>
}