package com.prography.quest.data.repository

import androidx.lifecycle.LiveData
import com.prography.quest.data.db.BookmarkDatabase
import com.prography.quest.data.model.BookmarkEntity
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

    override fun getBookmarkPhoto(): LiveData<List<BookmarkEntity>> {
        return db.bookmarkDao().getBookmarkPhoto()
    }
}