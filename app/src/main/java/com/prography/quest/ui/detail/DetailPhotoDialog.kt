package com.prography.quest.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.prography.quest.R
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.DetailPhotoDialogBinding
import com.prography.quest.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPhotoDialog : DialogFragment() {
    private var _binding: DetailPhotoDialogBinding? = null
    private val binding get() = _binding!!

    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModels()
    private val args by navArgs<DetailPhotoDialogArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailPhotoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmark = args.bookmark

        photoDetailsViewModel.getPhotoDetails(bookmark.id)
        bookmarkColor(bookmark.id)

        observer()

        binding.bookmarkBtn.setOnClickListener {
            photoDetailsViewModel.toggleBookmarkButton(bookmark)
            bookmarkColor(bookmark.id)
        }

        binding.exitBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            photoDetailsViewModel.photoDetails.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                    }

                    is UiState.Success -> {
                        binding.userName.text = it.data.user.username
                        binding.photo.load(it.data.urls.regular)
                        binding.description.text = it.data.description
                    }

                    is UiState.Failure -> {
                    }
                }
            }
        }
    }

    private fun bookmarkColor(id: String) {
        lifecycleScope.launch {
            val isBookmarked = photoDetailsViewModel.getIsBookmarked(id).first()

            binding.bookmarkBtn.setColorFilter(
                if (isBookmarked)
                    ContextCompat.getColor(requireContext(), R.color.white)
                else
                    ContextCompat.getColor(requireContext(), R.color.gray95)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}