package com.medhdj.features.highlights.data

import com.medhdj.features.highlights.domain.Highlight
import io.reactivex.Observable
import javax.inject.Inject

interface FootballHighlightsRepository {
    fun fetchHighlights(): Observable<List<Highlight>>

    class Network @Inject constructor(
        private val highlightsApi: HighlightsApi
    ) : FootballHighlightsRepository {
        override fun fetchHighlights(): Observable<List<Highlight>> =
            highlightsApi
                .highlights()
                .map {
                    it.response.toHighlights()
                }
    }
}
