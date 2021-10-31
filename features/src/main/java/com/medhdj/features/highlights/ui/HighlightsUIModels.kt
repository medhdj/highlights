package com.medhdj.features.highlights.ui

import com.google.android.exoplayer2.MediaItem

data class HighlightsItemUIModel(val title: String)

data class HighlightsDetailsUIModel(
    val title: String,
    val videoMediaItem: MediaItem
)
