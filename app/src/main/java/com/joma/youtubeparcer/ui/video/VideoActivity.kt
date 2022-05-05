package com.joma.youtubeparcer.ui.video

import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.joma.youtubeparcer.base.BaseActivity
import com.joma.youtubeparcer.databinding.ActivityVideoBinding
import com.joma.youtubeparcer.utils.YOUTUBE_URL
import com.joma.youtubeparcer.utils.isOnline
import com.joma.youtubeparcer.utils.visible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoActivity : BaseActivity<ActivityVideoBinding>(ActivityVideoBinding::inflate),
    Player.Listener {

    private var id: String? = null
    private var title: String? = null
    private var des: String? = null

    /* private var mPlayer: ExoPlayer? = null
     private var playWhenReady = true
     private var currentWindow = 0
     private var playbackPosition = 0L*/
    private lateinit var youtubeLink: String

    override fun chekInternet() {
        if (isOnline()) {
            setupUI()
        } else {
            binding.checkInternet.root.visible()
        }
    }

    private fun initYoutubePlayer(id: String) {
        lifecycle.addObserver(binding.youtubePlayer)
        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(YOUTUBE_URL + id, 0f)
            }
        })
        /*mPlayer = ExoPlayer.Builder(this).build()
        binding.youtubePlayer.player = mPlayer

        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {

                    val iTag = 137//tag of video 1080
                    val audioTag = 140 //tag m4a audio
                    // 720, 1080, 480
                    var videoUrl = ""
                    val iTags: List<Int> = listOf(22, 137, 18)
                    for (i in iTags) {
                        val ytFile = ytFiles.get(i)
                        if (ytFile != null) {
                            val downloadUrl = ytFile.url
                            if (downloadUrl != null && downloadUrl.isNotEmpty()) {
                                videoUrl = downloadUrl
                            }
                        }
                    }
                    if (videoUrl == "")
                        videoUrl = ytFiles[iTag].url
                    val audioUrl = ytFiles[audioTag].url
                    val audioSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(audioUrl))
                    val videoSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(videoUrl))
                    mPlayer?.setMediaSource(
                        MergingMediaSource(true, videoSource, audioSource), true
                    )
                    mPlayer?.prepare()
                    mPlayer?.playWhenReady = playWhenReady
                    mPlayer?.seekTo(currentWindow, playbackPosition)
                    mPlayer?.addListener(this@VideoActivity)
                }
            }

        }.extract(youtubeLink, false, true)

    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24 || mPlayer == null) {
            initYoutubePlayer()

        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || mPlayer == null) {
            initYoutubePlayer()


        }
    }

    override fun onPause() {
        if (Util.SDK_INT < 24) releasePlayer()
        super.onPause()
    }

    private fun releasePlayer() {
        if (mPlayer != null) {
            playWhenReady = mPlayer!!.playWhenReady
            playbackPosition = mPlayer!!.currentPosition
            currentWindow = mPlayer!!.currentMediaItemIndex
            mPlayer?.release()
            mPlayer = null
        }
*/
    }

    private fun setupUI() {
        val extras = intent.extras
        id = extras?.getString("videoId")
        title = extras?.getString("title")
        des = extras?.getString("des")
        id?.let { initYoutubePlayer(it) }
        binding.tvTitle.text = title
        binding.tvDes.text = des
    }
}

