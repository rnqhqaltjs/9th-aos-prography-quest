package com.prography.quest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prography.quest.data.model.BookmarkEntity

@Database(
    entities = [BookmarkEntity::class],
    version = 1,
    exportSchema = false
)

abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

}