package com.medhdj.features.highlights.domain

import com.medhdj.core.usecase.BaseUseCase
import com.medhdj.core.usecase.NoParams
import com.medhdj.features.highlights.data.FootballHighlightsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetHighlightsUseCase @Inject constructor(
    private val footballHighlightsRepository: FootballHighlightsRepository
) : BaseUseCase<List<Highlight>, NoParams> {
    override fun run(params: NoParams): Observable<List<Highlight>> =
        footballHighlightsRepository.fetchHighlights()
}
