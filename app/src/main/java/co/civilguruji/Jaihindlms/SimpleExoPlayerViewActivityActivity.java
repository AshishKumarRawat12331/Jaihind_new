package co.civilguruji.Jaihindlms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class SimpleExoPlayerViewActivityActivity extends AppCompatActivity {
    // Replace video id with your video Id
    private String YOUTUBE_VIDEO_ID = "uZnWUZW1hQo";
    private String BASE_URL = "https://www.youtube.com";
    private String mYoutubeLink = BASE_URL + "/watch?v=" + YOUTUBE_VIDEO_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_exo_player_view_activity);
        extractYoutubeUrl();
    }

    private void extractYoutubeUrl() {
        @SuppressLint("StaticFieldLeak") YouTubeExtractor mExtractor = new YouTubeExtractor(this) {
            @Override
            protected void onExtractionComplete(SparseArray<YtFile> sparseArray, VideoMeta videoMeta) {
                if (sparseArray != null) {
                    playVideo(sparseArray.get(17).getUrl());
                }
            }
        };
        mExtractor.extract(mYoutubeLink, true, true);
    }

    private void playVideo(String downloadUrl) {

      }


}

/*extends AppCompatActivity {

    // creating a variable for exoplayerview.
    SimpleExoPlayerView exoPlayerView;

    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;

   String video_id;

    // url of video which we are loading.
  //  String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
   // String videoURL = "https://www.youtube.com/watch?v=DK_UsATwoxI";
    String youtubeLink = "http://youtube.com/watch?v=xxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_exo_player_view_activity);


        exoPlayerView = findViewById(R.id.idExoPlayerVIew);

        video_id=getIntent().getStringExtra("video_id");

        String youtubeLink = "http://youtube.com/watch?v=xxxx";

        new YouTubeExtractor(this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                    String downloadUrl = ytFiles.get(itag).getUrl();

                    Log.e("downloadUrl",""+ downloadUrl );

                }
            }
        }.extract(youtubeLink);


     *//*   try {

            // bandwisthmeter is used for
            // getting default bandwidth
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

            // track selector is used to navigate between
            // video using a default seekbar.
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            // we are parsing a video url
            // and parsing its video uri.
            Uri videouri = Uri.parse(videoURL);

            // we are creating a variable for datasource factory
            // and setting its user agent as 'exoplayer_view'
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

            // we are creating a variable for extractor factory
            // and setting it to default extractor factory.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);

            // inside our exoplayer view
            // we are setting our player
            exoPlayerView.setPlayer(exoPlayer);

            // we are preparing our exoplayer
            // with media source.
            exoPlayer.prepare(mediaSource);

            // we are setting our exoplayer
            // when it is ready.
            exoPlayer.setPlayWhenReady(true);

        } catch (Exception e) {
            // below line is used for
            // handling our errors.
          //  Log.e("TAG", "Error : " + e.toString());
        }*//*


    }
}

*//*extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_exo_player_view_activity);

    }
}*/