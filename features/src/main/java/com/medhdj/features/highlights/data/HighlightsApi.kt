package com.medhdj.features.highlights.data

import io.reactivex.Observable
import retrofit2.http.GET

interface HighlightsApi {
    @GET(".")
    fun highlights(): Observable<HighlightsResponse>
}
