package com.prography.quest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.prography.quest.R
import com.prography.quest.databinding.DetailPhotoDialogBinding
import com.prography.quest.util.UiState
import com.prography.quest.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        _binding = DataBindingUtil.inflate(inflater, R.layout.detail_photo_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = photoDetailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.bookmark = args.bookmark

        photoDetailsViewModel.getPhotoDetails(args.bookmark.id)
        observer()

        binding.exitBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observer() {
        collectLatestStateFlow(photoDetailsViewModel.photoDetails) {
            when (it) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    binding.apply {
                        title.text = it.data.user.name
                        userName.text = it.data.user.username
                        photo.load(it.data.urls.regular)
                        description.text = it.data.description
                    }
                }
                is UiState.Failure -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}