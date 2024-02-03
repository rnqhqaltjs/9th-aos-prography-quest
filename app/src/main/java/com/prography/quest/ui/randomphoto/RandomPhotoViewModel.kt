package com.prography.quest.ui.randomphoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.data.repository.BookmarkRepository
import com.prography.quest.data.repository.RandomPhotoRepository
import com.prography.quest.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomPhotoViewModel @Inject constructor(
    private val randomPhotoRepository: RandomPhotoRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _randomResult = MutableStateFlow<UiState<List<PhotosResponseItem>>>(UiState.Loading)
    val randomResult = _randomResult.asStateFlow()

    fun getRandomPhoto() = viewModelScope.launch {
        try {
            val response = randomPhotoRepository.getRandomPhoto(10)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                _randomResult.value = UiState.Success(result)
            } else {
                _randomResult.value = UiState.Failure("Failed to load data")
            }
        } catch (e: Exception) {
            _randomResult.value = UiState.Failure("Unexpected error occurred")
        }
    }

    fun addBookmark(bookmarkEntity: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.insertBookmark(bookmarkEntity)
    }

    fun deleteBookmark(bookmarkEntity: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.deleteBookmark(bookmarkEntity)
    }
}