package co.civilguruji.Jaihindlms

import Jaihindlms.R
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_main_youtubeplay.*


class YoutubeVideoPlayNew  : AppCompatActivity() {


    private var videoPlayer: SimpleExoPlayer? = null
    private var sampleUrl = ""
    @SuppressLint("StaticFieldLeak")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()

        setContentView(R.layout.activity_main_youtubeplay)

      //  val youtubeLink = "http://youtube.com/watch?v=DK_UsATwoxI"
        val video_id_link=intent.getStringExtra("video_id")
        val youtubeLink = ""+video_id_link

        Log.e("video_id_link",""+ video_id_link);


        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
                if (ytFiles != null) {
                    val itag = 18
                    if (ytFiles[itag]!=null) {
                        sampleUrl = ytFiles[itag].url
                        initializePlayer()
                    }
                }
            }
        }.extract(youtubeLink)
    }

    private fun buildMediaSource(): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(sampleUrl))
    }

    private fun initializePlayer() {
        videoPlayer = SimpleExoPlayer.Builder(this).build()
        video_player_view?.player = videoPlayer
        buildMediaSource()?.let {
            videoPlayer?.prepare(it)
            videoPlayer?.playWhenReady = true

        }
    }

    override fun onResume() {
        super.onResume()
        videoPlayer?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        videoPlayer?.playWhenReady = false
        if (isFinishing) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        videoPlayer?.release()
    }
}