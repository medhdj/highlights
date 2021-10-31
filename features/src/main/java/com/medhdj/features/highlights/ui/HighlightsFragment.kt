package com.medhdj.features.highlights.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.medhdj.core.features.R
import com.medhdj.core.features.databinding.FragmentHighlightsBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HighlightsFragment : Fragment() {

    @Inject
    lateinit var highlightsListAdapter: HighlightsListAdapter

    private var _binding: FragmentHighlightsBinding? = null
    private val binding get() = _binding!!

    private val highlights by viewModels<HighlightsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHighlightsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        highlights.highlightsData.observe(viewLifecycleOwner) {
            it.either(::handleError, ::handleSuccess)
        }

        setupHighlightsList()
    }

    private fun setupHighlightsList() {
        binding.highlightsList.layoutManager = LinearLayoutManager(requireContext())
        binding.highlightsList.adapter = highlightsListAdapter
        highlightsListAdapter.itemClickListener = {
            val detailsBundle = bundleOf(
                HightlightDetailsViewModel.HIGHLIGHT_TITLE to it.title,
                HightlightDetailsViewModel.HIGHLIGHT_VIDEO_URL to it.title
            )
            findNavController().navigate(R.id.action_go_to_details, detailsBundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleSuccess(data: List<HighlightsItemUIModel>) {
        highlightsListAdapter.submitList(data)
    }

    private fun handleError(throwable: Throwable) {
        Timber.e(throwable)
    }
}