package com.prography.quest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.prography.quest.data.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)

    @Update
    suspend fun updateBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmarks")
    fun getBookmarkPhoto(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE id = :bookmarkId")
    fun getBookmarkDetail(bookmarkId: String): BookmarkEntity

    @Query("SELECT EXISTS(SELECT * FROM bookmarks WHERE id = :bookmarkId)")
    fun getBookmarkExist(bookmarkId: String): Flow<Boolean>
}