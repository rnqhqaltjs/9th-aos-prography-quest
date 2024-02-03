package com.prography.quest.data.repository

import com.prography.quest.data.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)

    suspend fun updateBookmark(bookmarkEntity: BookmarkEntity)

    fun getBookmarkPhoto(): Flow<List<BookmarkEntity>>

    fun getBookmarkDetail(id: String): BookmarkEntity

    fun getBookmarkExist(id: String): Flow<Boolean>
}