package com.prography.quest.di

import android.content.Context
import androidx.room.Room
import com.prography.quest.data.db.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Room
    @Singleton
    @Provides
    fun provideBookmarkDatabase(@ApplicationContext context: Context): BookmarkDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            BookmarkDatabase::class.java,
            "bookmark-photo"
        ).build()

}