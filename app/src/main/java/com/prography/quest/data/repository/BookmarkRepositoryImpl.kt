package com.prography.quest.data.repository

import com.prography.quest.data.local.BookmarkDatabase
import com.prography.quest.data.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val db: BookmarkDatabase
) : BookmarkRepository {

    override suspend fun insertBookmark(bookmarkEntity: BookmarkEntity) {
        db.bookmarkDao().insertBookmark(bookmarkEntity)
    }

    override suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity) {
        db.bookmarkDao().deleteBookmark(bookmarkEntity)
    }

    override suspend fun updateBookmark(bookmarkEntity: BookmarkEntity) {
        db.bookmarkDao().updateBookmark(bookmarkEntity)
    }

    override fun getBookmarkPhoto(): Flow<List<BookmarkEntity>> {
        return db.bookmarkDao().getBookmarkPhoto()
    }

    override fun getBookmarkDetail(id: String): BookmarkEntity {
        return db.bookmarkDao().getBookmarkDetail(id)
    }

    override fun getBookmarkExist(id: String): Flow<Boolean> {
        return db.bookmarkDao().getBookmarkExist(id)
    }


}