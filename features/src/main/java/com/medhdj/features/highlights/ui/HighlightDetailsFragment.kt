package com.medhdj.features.highlights.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.medhdj.core.features.databinding.FragmentDetailsBinding
import com.medhdj.features.common.PlayerSetupHelper
import com.medhdj.features.common.PlayerSetupHelper.player
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HighlightDetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val detailsViewModel by viewModels<HightlightDetailsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPlayer()

        detailsViewModel.hightlightDetailsData.observe(viewLifecycleOwner) {
            it.either(::handleError, ::handleSuccess)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpPlayer() {
        PlayerSetupHelper.setUp(requireContext(), viewLifecycleOwner, binding.playerView)
    }

    private fun handleSuccess(highlightsDetailsUIModel: HighlightsDetailsUIModel) {
        binding.highlightTitle.text = highlightsDetailsUIModel.title
        player?.setMediaItem(highlightsDetailsUIModel.videoMediaItem, player?.contentPosition ?: 0)
        player?.addMediaItem(MediaItem.fromUri(VideoType.MP3.url))

    }

    private fun handleError(throwable: Throwable) {
        Toast.makeText(requireContext(), "Something went Wrong!", LENGTH_LONG).show()
    }
}
