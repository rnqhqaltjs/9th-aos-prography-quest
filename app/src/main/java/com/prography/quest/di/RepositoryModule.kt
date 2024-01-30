package com.prography.quest.di

import com.prography.quest.data.repository.PhotosRepository
import com.prography.quest.data.repository.PhotosRepositoryImpl
import com.prography.quest.data.repository.RandomPhotoRepository
import com.prography.quest.data.repository.RandomPhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPhotosRepository(
        photosRepositoryImpl: PhotosRepositoryImpl,
    ): PhotosRepository

    @Singleton
    @Binds
    abstract fun bindRandomPhotoRepository(
        randomPhotoRepositoryImpl: RandomPhotoRepositoryImpl,
    ): RandomPhotoRepository
}