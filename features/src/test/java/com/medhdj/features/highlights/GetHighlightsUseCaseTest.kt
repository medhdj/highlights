package com.medhdj.core.features.highlights

import com.medhdj.core.usecase.NoParams
import com.medhdj.features.highlights.data.FootballHighlightsRepository
import com.medhdj.features.highlights.domain.GetHighlightsUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test

class GetHighlightsUseCaseTest {
    private val footballHighlightsRepository: FootballHighlightsRepository = mockk()
    private val tested = GetHighlightsUseCase(footballHighlightsRepository)

    @Test
    fun `test successful fetch of highlights `() {
        // given
        every { footballHighlightsRepository.fetchHighlights() } returns Observable.just(
            listOf(
                mockk()
            )
        )

        // when
        val observer = tested.run(NoParams).test()

        // then
        observer.assertValue {
            it.size == 1
        }
        observer.dispose()
    }
}
