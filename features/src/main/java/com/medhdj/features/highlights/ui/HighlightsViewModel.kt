package com.medhdj.features.highlights.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.medhdj.core.extension.mapAndConvertToLiveDataInBackground
import com.medhdj.core.functionnal.Response
import com.medhdj.core.usecase.NoParams
import com.medhdj.features.highlights.domain.GetHighlightsUseCase
import com.medhdj.features.highlights.domain.Highlight
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HighlightsViewModel @Inject constructor(
    private val getHighlightsUseCase: GetHighlightsUseCase
) : ViewModel() {

    private val _highlightsData = MutableLiveData<Response<Throwable, List<HighlightsItemUIModel>>>()
    val highlightsData: LiveData<Response<Throwable, List<HighlightsItemUIModel>>> = _highlightsData

    init {
        fetchHighlights()
    }

    private fun fetchHighlights() {
        getHighlightsUseCase
            .run(NoParams)
            .mapAndConvertToLiveDataInBackground(
                successHandler = {
                    it.toHighlightsItemViewList()
                },
                errorHandler = {
                    it
                },
                liveData = _highlightsData
            )
    }
}

private fun List<Highlight>.toHighlightsItemViewList() =
    map {
        HighlightsItemUIModel(it.title)
    }
