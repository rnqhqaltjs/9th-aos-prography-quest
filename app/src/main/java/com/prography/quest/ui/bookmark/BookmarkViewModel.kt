package com.prography.quest.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.PhotosResponseItem
import com.prography.quest.data.repository.BookmarkRepository
import com.prography.quest.data.repository.PhotosRepository
import com.prography.quest.data.repository.RandomPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val randomPhotoRepository: RandomPhotoRepository,
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    private val _getRandomResult = MutableLiveData<List<PhotosResponseItem>>()
    val getRandomResult: LiveData<List<PhotosResponseItem>> get() = _getRandomResult

    fun getRandomPhoto() = viewModelScope.launch {
        val response = randomPhotoRepository.getRandomPhoto(5)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _getRandomResult.postValue(body)
            }
        }
    }

    fun addBookmark(bookmarkEntity: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.insertBookmark(bookmarkEntity)
    }

    fun deleteBookmark(bookmarkEntity: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.deleteBookmark(bookmarkEntity)
    }
}