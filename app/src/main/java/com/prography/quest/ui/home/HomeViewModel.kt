package com.prography.quest.ui.home

import android.provider.Settings.Global.getString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prography.quest.R
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.data.model.photosresponse.PhotosResponseItem
import com.prography.quest.data.repository.BookmarkRepository
import com.prography.quest.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    val bookmarkPhoto: StateFlow<UIState> = bookmarkRepository.getBookmarkPhoto()
        .map { UIState.Success(it) }
        .catch { UIState.Error("Failed to load data") }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UIState.Loading)

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

    sealed class UIState {
        object Loading : UIState()
        data class Success(val data: List<BookmarkEntity>) : UIState()
        data class Error(val message: String) : UIState()
    }
}

