package com.prography.quest.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prography.quest.data.api.PhotoService
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.util.Constants.PAGING_SIZE
import java.io.IOException

class PhotosPagingSource(
    private val order_by: String,
    private val photoService: PhotoService
) : PagingSource<Int, PhotosResponseItem>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosResponseItem> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = photoService.getPhotos(pageNumber, params.loadSize, order_by)

            val data = response.body()
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (response.body()?.isEmpty() == true) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }
            LoadResult.Page(
                data = data ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotosResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}