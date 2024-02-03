package com.prography.quest.ui.randomphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.snackbar.Snackbar
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.FragmentPhotoRandomBinding
import com.prography.quest.util.UiState
import com.prography.quest.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomPhotoFragment : Fragment() {
    private var _binding: FragmentPhotoRandomBinding? = null
    private val binding get() = _binding!!

    private val randomPhotoViewModel: RandomPhotoViewModel by viewModels()
    private lateinit var randomPhotoAdapter: RandomPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomPhotoViewModel.getRandomPhoto()
        setupRecyclerView()

        observer()

    }

    private fun observer() {
        collectLatestStateFlow(randomPhotoViewModel.getRandomResult) {
            when (it) {
                is UiState.Loading -> {}
                is UiState.Success -> randomPhotoAdapter.submitList(it.data)
                is UiState.Failure -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        randomPhotoAdapter = RandomPhotoAdapter()
        binding.RandomPhotoRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = randomPhotoAdapter

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }

        randomPhotoAdapter.setOnBookmarkClickListener { clickedItem, clickedPosition ->
            randomPhotoViewModel.addBookmark(
                BookmarkEntity(
                    clickedItem.id,
                    clickedItem.description,
                    clickedItem.urls.regular,
                    clickedItem.user.name,
                    clickedItem.user.username,
                )
            )
            binding.RandomPhotoRecyclerView.smoothScrollToPosition(clickedPosition + 1)

            Snackbar.make(requireView(), "북마크 완료", Snackbar.LENGTH_SHORT).apply {
                setAction("취소") {
                    randomPhotoViewModel.deleteBookmark(
                        BookmarkEntity(
                            clickedItem.id,
                            clickedItem.description,
                            clickedItem.urls.regular,
                            clickedItem.user.name,
                            clickedItem.user.username,
                        )
                    )
                    binding.RandomPhotoRecyclerView.smoothScrollToPosition(clickedPosition)
                }
            }.show()
        }

        randomPhotoAdapter.setOnInformationClickListener {
            val action = RandomPhotoFragmentDirections.actionRandomPhotoFragmentToDetailPhotoDialog(
                BookmarkEntity(it.id, it.description, it.urls.regular, it.user.name, it.user.username)
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}