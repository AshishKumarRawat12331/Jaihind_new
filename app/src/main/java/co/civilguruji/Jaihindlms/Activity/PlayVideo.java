package co.civilguruji.Jaihindlms.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.gson.Gson;
import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ChannelVideoListVideoScreenAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;

public class PlayVideo extends AppCompatActivity  implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    // private YouTubePlayerView youTubeView;
    private YouTubePlayerSupportFragment youTubeView;
    private YouTubePlayer player;
    String video="";
    String Title="";
    String response="";

    TextView tvVideoTitle;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_play_video);

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
        // youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_view);
        youTubeView.initialize(ApplicationConstant.INSTANCE.YOUTUBE_API_KEY, this);

        dataParsetopvideo(response);


       /* 2 line m
       *
       *
       * */




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
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;

        if (!wasRestored) {

            Log.v("video",video);
            player.cueVideo(video.replace("?t=6",""));
            player.setFullscreen(true);
            player.play();

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(ApplicationConstant.INSTANCE.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }


}
