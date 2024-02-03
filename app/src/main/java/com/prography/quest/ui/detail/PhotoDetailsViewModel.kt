package com.prography.quest.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.data.repository.BookmarkRepository
import com.prography.quest.data.repository.PhotosRepository
import com.prography.quest.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _photoDetails = MutableStateFlow<UiState<PhotosResponseItem>>(UiState.Loading)
    val photoDetails = _photoDetails.asStateFlow()

    fun getPhotoDetails(id: String) = viewModelScope.launch {
        try {
            val response = photosRepository.getPhotoDetails(id)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                _photoDetails.value = UiState.Success(result)
            } else {
                _photoDetails.value = UiState.Failure("Failed to load data")
            }
        } catch (e: Exception) {
            _photoDetails.value = UiState.Failure("Unexpected error occurred")
        }
    }

    fun getIsBookmarked(id: String): StateFlow<Boolean> {
        val initialBookmarkStatus = runBlocking {
            bookmarkRepository.getBookmarkExist(id).first()
        }
        return bookmarkRepository.getBookmarkExist(id)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = initialBookmarkStatus
            )
    }

    fun toggleBookmarkButton(bookmarkEntity: BookmarkEntity) {
        viewModelScope.launch {
            if (getIsBookmarked(bookmarkEntity.id).value) {
                bookmarkRepository.deleteBookmark(bookmarkEntity)
            } else {
                bookmarkRepository.insertBookmark(bookmarkEntity)
            }
        }
    }
}