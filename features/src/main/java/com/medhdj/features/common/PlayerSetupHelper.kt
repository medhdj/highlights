package com.medhdj.features.common

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.medhdj.core.platform.LifecycleEventDispatcher
import timber.log.Timber

object PlayerSetupHelper {
    private var _player: SimpleExoPlayer? = null
    val player: SimpleExoPlayer?
        get() = _player

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    private val playbackStateListener = playbackStateListener()

    fun setUp(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        playerView: PlayerView
    ) {
        LifecycleEventDispatcher(
            lifecycleOwner,
            onStart = {
                if (Util.SDK_INT >= 24) {
                    initializePlayer(context, playerView)
                }
            },
            onResume = {
                hideSystemUi(playerView)
                if ((Util.SDK_INT < 24 || _player == null)) {
                    initializePlayer(context, playerView)
                }
            },
            onPause = {
                if (Util.SDK_INT < 24) {
                    releasePlayer()
                }
            },
            onStop = {
                if (Util.SDK_INT >= 24) {
                    releasePlayer()
                }
            }
        )
    }

    // initialization
    private fun initializePlayer(context: Context, playerView: PlayerView) {
        val trackSelector = DefaultTrackSelector(context)
        _player = SimpleExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                playerView.player = exoPlayer
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()

                exoPlayer.addListener(playbackStateListener)
            }
    }

    private fun releasePlayer() {
        _player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentWindowIndex
            playWhenReady = exoPlayer.playWhenReady

            exoPlayer.removeListener(playbackStateListener)

            exoPlayer.release()
        }
        _player = null
    }

    // UI
    private fun hideSystemUi(playerView: PlayerView) {
        playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    // listeners
    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Timber.d("changed state to $stateString")
        }
    }
}

