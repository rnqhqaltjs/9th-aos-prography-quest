package com.prography.quest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.data.repository.BookmarkRepository
import com.prography.quest.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    private val _photos = MutableLiveData<List<PhotosResponseItem>>()
    val photos: LiveData<List<PhotosResponseItem>> get() = _photos

    fun getPhotos() = viewModelScope.launch {
        val response = photosRepository.getPhotos(1, 10, "latest")
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _photos.postValue(body)
            }
        }
    }

    val bookmarkPhoto: LiveData<List<BookmarkEntity>> = bookmarkRepository.getBookmarkPhoto()
}