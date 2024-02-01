package com.prography.quest.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val username: String
):Parcelable