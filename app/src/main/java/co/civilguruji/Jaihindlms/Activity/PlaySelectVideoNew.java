package co.civilguruji.Jaihindlms.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ChannelVideoListVideoScreenAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

public class PlaySelectVideoNew extends AppCompatActivity   {
    private static final int RECOVERY_REQUEST = 1;
    String video="";
    String Title="";
    String response="";
    TextView tvVideoTitle;
    RecyclerView recyclerView;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        setContentView(R.layout.activity_play_video_new);

        youTubePlayerView = findViewById(R.id.youtube_player_view);

        youtube_VIdeo();

        recyclerView=findViewById(R.id.recyclerView);

        // Set toolbar icon in ...

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Video");
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvVideoTitle=(TextView)findViewById(R.id.tv_video_title);
        video=  getIntent().getStringExtra("videoid");
        Title=  getIntent().getStringExtra("discriptiom");
        response=  getIntent().getStringExtra("response");

        tvVideoTitle.setText(Title);

       // dataParsetopvideo(response);

    }

    private void youtube_VIdeo() {

        youTubePlayerView = findViewById(R.id.youtube_player_view);

        youTubePlayerView.enterFullScreen();
        youTubePlayerView.toggleFullScreen();


        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.getPlayerUiController();

        // below line is to enter full screen mode.
        youTubePlayerView.enterFullScreen();
        youTubePlayerView.toggleFullScreen();


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                Log.v("videovvvvvv",video);

                youTubePlayer.loadVideo(video.replace("?t=6",""),0);

            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
                                      @NonNull PlayerConstants.PlayerState state) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state);

            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        });

    }

    ChannelVideoResponse sliderImage;
    ArrayList<SetChannelVideo> operator = new ArrayList<>();
    ChannelVideoListVideoScreenAdapter mAdapterVideo;

    public void dataParsetopvideo(String response) {

        Gson gson = new Gson();
        sliderImage = gson.fromJson(response, ChannelVideoResponse.class);
        operator = sliderImage.getData();

        if (operator != null && operator.size() > 0) {

            mAdapterVideo = new ChannelVideoListVideoScreenAdapter(operator,this,response);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);

           // mLayoutManager = new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(mLayoutManager);


            // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
             recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapterVideo);

        } else {


        }

    }


}
