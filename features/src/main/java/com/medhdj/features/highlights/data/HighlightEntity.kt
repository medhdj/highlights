package com.medhdj.features.highlights.data

import com.medhdj.features.highlights.domain.Highlight

data class HighlightEntity(
    val title: String,
    val competition: String
)

fun HighlightEntity.toHighlight() =
    Highlight(
        title = title,
        competition = competition
    )

fun List<HighlightEntity>.toHighlights() =
    map {
        it.toHighlight()
    }