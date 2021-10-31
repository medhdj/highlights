package com.medhdj.features.highlights.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.MimeTypes
import com.medhdj.core.functionnal.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HightlightDetailsViewModel @Inject constructor(private val state: SavedStateHandle) :
    ViewModel() {
    companion object {
        const val HIGHLIGHT_TITLE = "highlight_title"
        const val HIGHLIGHT_VIDEO_URL = "VIDEO_URL"
    }


    val hightlightDetailsData: LiveData<Response<Throwable, HighlightsDetailsUIModel>>
        get() = _hightlightDetailsData

    private val _hightlightDetailsData: MutableLiveData<Response<Throwable, HighlightsDetailsUIModel>> =
        MutableLiveData<Response<Throwable, HighlightsDetailsUIModel>>()


    init {
        _hightlightDetailsData.postValue(extractDataFromArguments())
    }

    private fun extractDataFromArguments(): Response<Throwable, HighlightsDetailsUIModel> {
        val title = state.get<String>(HIGHLIGHT_TITLE)
        val videoUrl = state.get<String>(HIGHLIGHT_VIDEO_URL)
        return when {
            title.isNullOrEmpty() || videoUrl.isNullOrEmpty() ->
                Response.Failure(Exception("something wrong!"))
            else -> Response.Success(
                HighlightsDetailsUIModel(
                    title,
                    videoMediaItem = createVideoMediaItem(VideoType.DASH)
                )
            )
        }
    }

    private fun createVideoMediaItem(type: VideoType): MediaItem =
        when (type) {
            VideoType.MP3, VideoType.MP4 -> {
                MediaItem.fromUri(type.url)
            }
            VideoType.DASH -> {
                MediaItem.Builder()
                    .setUri(type.url)
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()
            }
        }
}

enum class VideoType(val url: String) {
    MP3("https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"),
    MP4("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"),
    DASH("https://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0")
}
