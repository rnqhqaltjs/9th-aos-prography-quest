package com.prography.quest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.data.remote.PhotoService
import com.prography.quest.util.Constants.PAGING_SIZE
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val photoService: PhotoService
) : PhotosRepository {

    override suspend fun getPhotos(
        page: Int,
        per_page: Int,
        order_by: String
    ): Response<List<PhotosResponseItem>> {
        return photoService.getPhotos(page, per_page, order_by)
    }

    override suspend fun getPhotoDetails(id: String): Response<PhotosResponseItem> {
        return photoService.getPhotoDetails(id)
    }

    override fun getPhotosPaging(order_by: String): Flow<PagingData<PhotosResponseItem>> {
        val pagingSourceFactory = { PhotosPagingSource(order_by, photoService) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = PAGING_SIZE * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}