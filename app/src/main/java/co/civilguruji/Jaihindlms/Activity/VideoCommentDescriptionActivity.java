package co.civilguruji.Jaihindlms.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ContectDetailListByIdAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.CoursesContentFragment;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.VideoCommentFragment;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.VideoDescriptionFragment;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.Channelsub;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;
import co.civilguruji.Jaihindlms.YoutubeVideoPlayNew;

public class VideoCommentDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    String response;
    PartsChannelVideo operator;
    TextView Comment_tv,description_tv;
    Loader loader;
    String youtubelink;
    TextView tittle ;
    ImageView fullscreen_button;
    ImageView video_imahe;
    LinearLayout play_video;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_video_part_description);


        Getid();


    }

    private void Getid() {

        loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);
        play_video = findViewById(R.id.play_video);

        video_imahe = findViewById(R.id.video_imahe);
        fullscreen_button = findViewById(R.id.fullscreen_button);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
         play_video.setOnClickListener(this);

        fullscreen_button.setOnClickListener(this);

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


        Comment_tv=findViewById(R.id.Comment_tv);
        description_tv=findViewById(R.id.description_tv);
        Comment_tv.setOnClickListener(this);
        description_tv.setOnClickListener(this);
        tittle=findViewById(R.id.tittle);
        tittle.setOnClickListener(this);

        response=getIntent().getStringExtra("response");

        Gson gson = new Gson();
        operator = gson.fromJson(response, PartsChannelVideo.class);
        tittle.setText("  "+operator.getTitle() );

        Log.e("Description",""+ operator.getDescription());

        UtilMethods.INSTANCE.part_description(this,""+operator.getDescription());
        UtilMethods.INSTANCE.Get_part_video_id(this,operator.getId()+""  );

        // Toast.makeText(this, ""+operator.getId(), Toast.LENGTH_SHORT).show();

        //  data_priseList( prisedetail);
        //    UtilMethods.INSTANCE.Get_Course_id(this,operator.getId()+"" ,""+response );
        //   UtilMethods.INSTANCE.DemoVideocontentdetails(this,operator.getId()+"",null);

        final String videolinkre=""+ operator.getUpload_video().replace("https://youtu.be/","");
        String img_url="http://img.youtube.com/vi/"+videolinkre.replace("?t=6","")+"/0.jpg"; // this is link which will give u thumnail image of that video

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(this)
                .load( ""+img_url)
                .apply(requestOptions)
                .into(video_imahe);

        Comment_tv.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        description_tv.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        Comment_tv.setTextColor(getResources().getColorStateList(R.color.white));

        description_tv.setTextColor(getResources().getColorStateList(R.color.black));

        changeFragment(new VideoCommentFragment());


        youtube_VIdeo();


    }


    private void youtube_VIdeo() {

        youtubelink=""+ operator.getUpload_video().toString().trim();




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

                Log.v("videovvvvvv",youtubelink);

                // https://youtu.be/gVMElYSJ7L4

                String youtube_link=""+youtubelink.replace("https://youtu.be/","");

                youTubePlayer.loadVideo(youtube_link.replace("?t=6",""),0);

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




    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {



        if (activityFragmentMessage.getFrom().equalsIgnoreCase("Successful")) {


        }


    }

    ContectDetailListByIdAdapter mAdapter_content;
    ArrayList<PartsChannelVideo> transactionscontent = new ArrayList<>();
    Channelsub contenttransactions = new Channelsub();



    private void changeFragment(Fragment targetFragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }


    @Override
    public void onClick(View view) {


        if(view==fullscreen_button){

            youtubelink=""+ operator.getUpload_video().toString().trim();
            Intent i = new Intent(this, YoutubeVideoPlayNew.class);
            i.putExtra("video_id",""+youtubelink);
            startActivity(i);


        }


       if(view==play_video){

            youtubelink=""+ operator.getUpload_video().toString().trim();
            Intent i = new Intent(this, YoutubeVideoPlayNew.class);
            i.putExtra("video_id",""+youtubelink);
            startActivity(i);


        }





          if(view==Comment_tv){

            FragmentManager fragmentManager = getSupportFragmentManager();
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            final CoursesContentFragment myFragment = new CoursesContentFragment();

              Comment_tv.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
              description_tv.setBackgroundTintList(getResources().getColorStateList(R.color.white));
              Comment_tv.setTextColor(getResources().getColorStateList(R.color.white));
              description_tv.setTextColor(getResources().getColorStateList(R.color.black));

            changeFragment(new VideoCommentFragment());


        }

        if(view==description_tv){

            description_tv.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
            Comment_tv.setBackgroundTintList(getResources().getColorStateList(R.color.white));
            description_tv.setTextColor(getResources().getColorStateList(R.color.white));
            Comment_tv.setTextColor(getResources().getColorStateList(R.color.black));

            changeFragment(new VideoDescriptionFragment());


        }

    }





}