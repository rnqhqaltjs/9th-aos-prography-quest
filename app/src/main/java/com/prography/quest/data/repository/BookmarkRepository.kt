package com.prography.quest.data.repository

import androidx.lifecycle.LiveData
import com.prography.quest.data.db.BookmarkDao
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.PhotosResponseItem
import retrofit2.Response

interface BookmarkRepository {

    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)

    fun getBookmarkPhoto(): LiveData<List<BookmarkEntity>>
}