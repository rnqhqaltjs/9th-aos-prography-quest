package com.prography.quest.ui.randomphoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.snackbar.Snackbar
import com.prography.quest.data.model.BookmarkEntity
import com.prography.quest.databinding.FragmentPhotoRandomBinding
import dagger.hilt.android.AndroidEntryPoint

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

        randomPhotoViewModel.getRandomResult.observe(viewLifecycleOwner) {
            randomPhotoAdapter.submitList(it)
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
                    clickedItem.user.username,
                    true
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
                            clickedItem.user.username,
                            true
                        )
                    )
                    binding.RandomPhotoRecyclerView.smoothScrollToPosition(clickedPosition)
                }
            }.show()
        }

        randomPhotoAdapter.setOnInformationClickListener {
            val action = RandomPhotoFragmentDirections.actionRandomPhotoFragmentToDetailPhotoDialog(it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}