package co.civilguruji.Jaihindlms.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ChannelVideoListVideoScreenAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

public class PlayVideoNew extends AppCompatActivity implements View.OnClickListener {

    private static final int RECOVERY_REQUEST = 1;
    String video="";
    String Title="";
    String response="";
    TextView tvVideoTitle;
    RecyclerView recyclerView;
    YouTubePlayerView youTubePlayerView;
    float durationVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_play_video_new);


        youTubePlayerView = findViewById(R.id.youtube_player_view);

     /*   youTubePlayerView.enterFullScreen();
      //  youTubePlayerView.exitFullScreen();
        youTubePlayerView.isFullScreen();
        youTubePlayerView.toggleFullScreen();*/


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

        Log.e("videovvvvvv","As   :  "+ video);

        tvVideoTitle.setText(Title);

        tvVideoTitle.setOnClickListener(this);

        dataParsetopvideo(response);

    }

    private void youtube_VIdeo() {

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                Log.v("videovvvvvv",video);

                youTubePlayer.loadVideo(video.replace("?t=6",""),0);

/*
                youTubePlayerView.enterFullScreen();
                youTubePlayerView.exitFullScreen();
                youTubePlayerView.isFullScreen();
                youTubePlayerView.toggleFullScreen();*/

            }


            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
                                      @NonNull PlayerConstants.PlayerState state) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state);

            }

            @Override
            public void onVideoDuration(YouTubePlayer youTubePlayer, float duration) {
                super.onVideoDuration(youTubePlayer, duration);

                durationVideo=duration;

            }

            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);

                Log.e("durationsecond","second :   "+ second  +"  durationVideo    "+ durationVideo);

                if(durationVideo<=second){

                    finish();

                }

            }

            /* @Override
            public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float duration) {
                super.onVideoDuration(youTubePlayer, duration);

                durationVideo=duration;

            }


            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);

                Log.e("durationsecond","second :   "+ second  +"  durationVideo    "+ durationVideo);

                if(durationVideo<=second){

                  finish();

                }
            }
*/


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


    @Override
    public void onClick(View view) {


        if(view==tvVideoTitle){


            Intent i=new Intent(this, YoutubeFuuscreenActivity.class);
             i.putExtra("videoid",""+video.replace("?t=6",""));

            startActivity(i);

        }
    }
}
