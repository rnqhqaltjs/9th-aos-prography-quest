package com.prography.quest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.data.repository.BookmarkRepository
import com.prography.quest.data.repository.PhotosRepository
import com.prography.quest.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    val bookmarkPhoto: StateFlow<List<BookmarkEntity>> = bookmarkRepository.getBookmarkPhoto()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    private val _photosPaging = MutableStateFlow<PagingData<PhotosResponseItem>>(PagingData.empty())
    val photosPaging = _photosPaging.asStateFlow()

    fun getPhotosPaging() {
        viewModelScope.launch {
            photosRepository.getPhotosPaging("latest")
                .cachedIn(viewModelScope)
                .collect{
                    _photosPaging.value = it
                }
        }
    }
}