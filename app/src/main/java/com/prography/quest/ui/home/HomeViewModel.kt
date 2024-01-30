package com.prography.quest.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prography.quest.data.model.PhotosResponseItem
import com.prography.quest.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photosRepository: PhotosRepository
): ViewModel() {

    private val _getResult = MutableLiveData<List<PhotosResponseItem>>()
    val getResult: LiveData<List<PhotosResponseItem>> get() = _getResult

    fun getPhotos() = viewModelScope.launch {
        val response = photosRepository.getPhotos(1, 10, "latest")
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _getResult.postValue(body)
            }
        }
    }
}