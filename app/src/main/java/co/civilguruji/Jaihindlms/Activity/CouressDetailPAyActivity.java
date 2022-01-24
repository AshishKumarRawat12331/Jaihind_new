package co.civilguruji.Jaihindlms.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Jaihindlms.R;
import co.civilguruji.Jaihindlms.Adapter.ContectDetailListByIdAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.DataLogin;
import co.civilguruji.Jaihindlms.ApiRespose.DataUser;
import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.ApiRespose.priceResposeList;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.CoursesContentFragment;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.CoursesDetailFragment;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.Channelsub;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;
import co.civilguruji.Jaihindlms.YoutubeVideoPlayNew;

public class CouressDetailPAyActivity extends AppCompatActivity implements View.OnClickListener , PaymentResultListener {

    String response;
    String packagename;
    SetChannelVideo operator;
    TextView tittle,calender, time,mb_detail,audio_type, amount, Enrollnow ;
    TextView course,Details,pay_course,course_amount;
    LinearLayout li_course_amount,li_spimn;
    ImageView imagecouress;
    YouTubePlayerView youTubePlayerView;
    Loader loader;
    String CouressId="", finalpay="";
    TextView blink_animation;
    ImageView fullscreen_button;
    String video_id_cou;
    String youtubelink;

    YouTubePlayerView ytPlayer;
    ImageView video_imahe;
    LinearLayout play_video;
    Spinner prise_type;
    String prise_type_id="0";
    String prisedetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_couress_detail_pay);

        Getid();

        ShowBlinkText();

        setYoutube();

    }

    private void setYoutube() {

    }

    private void ShowBlinkText() {

        blink_animation=findViewById(R.id.blink_animation);

         final DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random R = new Random();
                        final float dx = R.nextFloat() * displaymetrics.widthPixels;
                        final float dy = R.nextFloat() * displaymetrics.heightPixels;
                        final Timer timer = new Timer();
                        blink_animation.animate()
                                .x(dx)
                                .y(dy)
                                .setDuration(20)
                                .start();
                    }
                });
            }
        }, 0, 2000);

        SharedPreferences myPreferences =   getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataLogin balanceCheckResponse = new Gson().fromJson(response, DataLogin.class);
        blink_animation.setText(""+balanceCheckResponse.getName() +"\n"+balanceCheckResponse.getUsername());

        Animation startAnimation = AnimationUtils.loadAnimation(this, R.anim.blinking_animation);
        blink_animation.startAnimation(startAnimation);


    }

    private void Getid() {
        prise_type=findViewById(R.id.prise_type);



        loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);


        play_video = findViewById(R.id.play_video);

        video_imahe = findViewById(R.id.video_imahe);
        fullscreen_button = findViewById(R.id.fullscreen_button);

        Enrollnow = findViewById(R.id.Enrollnow);
        amount = findViewById(R.id.amount);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        imagecouress=findViewById(R.id.imagecouress);

        play_video.setOnClickListener(this);
        Enrollnow.setOnClickListener(this);

        fullscreen_button.setOnClickListener(this);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Course Book");
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        li_course_amount=findViewById(R.id.li_course_amount);
        li_spimn=findViewById(R.id.li_spimn);
        course=findViewById(R.id.Course_tv);
        Details=findViewById(R.id.Details_tv);
        pay_course=findViewById(R.id.pay_course);
        course_amount=findViewById(R.id.course_amount);

        course.setOnClickListener(this);
        Details.setOnClickListener(this);
        pay_course.setOnClickListener(this);
        course_amount.setOnClickListener(this);
        li_course_amount.setOnClickListener(this);

        tittle=findViewById(R.id.tittle);
        calender=findViewById(R.id.calender);
        time=findViewById(R.id.time);
        mb_detail=findViewById(R.id.mb_detail);
        audio_type=findViewById(R.id.audio_type);

        tittle.setOnClickListener(this);

        response=getIntent().getStringExtra("response");
        prisedetail=getIntent().getStringExtra("prisedetail");
        finalpay=getIntent().getStringExtra("amountpk");
        packagename=getIntent().getStringExtra("packagename");
        CouressId=getIntent().getStringExtra("CouressId");

        amount.setText("Rs.  "+finalpay );

        Gson gson = new Gson();
        operator = gson.fromJson(response, SetChannelVideo.class);

        /*
         holder.title.setText("" + operator.getTitle());
         holder.amount.setText("" + operator.getCurrency() +" "+ operator.getPrice());
         holder.time.setText("" + operator.getDuration());
         */

     //   CouressId=""+ operator.getId();

        tittle.setText("  "+operator.getTitle());
        calender.setText("  01 August 2020");
        time.setText("  "+ operator.getDuration());
        mb_detail.setText("  197 MB");
        audio_type.setText("  Vendor supports this course");


      //  data_priseList( prisedetail);

        UtilMethods.INSTANCE.Get_Course_id(this,operator.getId()+"" ,""+response );


        UtilMethods.INSTANCE.DemoVideocontentdetails(this,operator.getId()+"",null);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(this)
                .load( ""+operator.getThumbnail())
                .apply(requestOptions)
                .into(imagecouress);

        //  name.setText("" + operator.getName());

        course.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        Details.setBackgroundTintList(getResources().getColorStateList(R.color.white));
        course.setTextColor(getResources().getColorStateList(R.color.white));

        Details.setTextColor(getResources().getColorStateList(R.color.black));

        changeFragment(new CoursesContentFragment());


    }

    ArrayList<String> Arealist = new ArrayList<String>();

    SetChannelVideo transactions = new SetChannelVideo();
    ArrayList<priceResposeList> operator_prise = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("contentDemoVideo")) {

            dataParse(activityFragmentMessage.getMessage());

        }

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("Failure")) {

            startPayment();

        }

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("Successful")) {



        }






    }

    ContectDetailListByIdAdapter mAdapter_content;
    ArrayList<PartsChannelVideo> transactionscontent = new ArrayList<>();
    Channelsub contenttransactions = new Channelsub();

    public void dataParse(String response) {

        Gson gson = new Gson();
        contenttransactions = gson.fromJson(response, Channelsub.class);
        transactionscontent = contenttransactions.getContents_meta();

        if (transactionscontent.size() > 0) {

            for(int i=0;i<transactionscontent.size();i++) {

                String  wishid = ""+transactionscontent.get(i).getOption().trim().toString();

                Log.e("videolink","As : "+ wishid);

                if(wishid.equalsIgnoreCase("video")){

                    youtubelink=""+ transactionscontent.get(i).getValue();

                    Log.e("videolinkEnter","As : "+ wishid +" Value : "+  transactionscontent.get(i).getValue());


                    youtube_VIdeo();



                }else if(wishid.equalsIgnoreCase("cover")){

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.rnd_logo);
                    requestOptions.error(R.drawable.rnd_logo);

                    Glide.with(this)
                            .load( ApplicationConstant.INSTANCE.baseUrl+transactionscontent.get(i).getValue())
                            .apply(requestOptions)
                            .into(video_imahe);

                }else {


                }
            }


        } else {

        }

    }

    private void youtube_VIdeo() {


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

    private void changeFragment(Fragment targetFragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    @Override
    public void onClick(View view) {

        if(view==play_video){

            Intent i = new Intent(this, YoutubeVideoPlayNew.class);
            i.putExtra("video_id",""+youtubelink);
            startActivity(i);

          /*  Intent i=new Intent(this, SimpleExoPlayerViewActivityActivity.class);
            i.putExtra("video_id",""+video_id_cou);
            startActivity(i);*/

        }

      if(view==fullscreen_button){

          Toast.makeText(this, "Assssss", Toast.LENGTH_SHORT).show();


          Intent i = new Intent(this, YoutubeVideoPlayNew.class);
          i.putExtra("video_id",""+youtubelink);
          startActivity(i);

         /* Intent i = new Intent(this, YoutubeVideoPlayNew.class);
          i.putExtra("video_id",""+youtubelink);
          startActivity(i);*/

        }

        if(view==Enrollnow){

            if(finalpay.toString().trim().equalsIgnoreCase("0")){

                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.Productgetfree(this,  ""+ CouressId ,loader,this,"package");

                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }


            }else {

                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.BuyCheck(this,  ""+ CouressId ,loader,this);

                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }


            }


        }

          if(view==course){

            FragmentManager fragmentManager = getSupportFragmentManager();
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            final CoursesContentFragment myFragment = new CoursesContentFragment();

            course.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
            Details.setBackgroundTintList(getResources().getColorStateList(R.color.white));
            course.setTextColor(getResources().getColorStateList(R.color.white));

            Details.setTextColor(getResources().getColorStateList(R.color.black));

            changeFragment(new CoursesContentFragment());


        }

        if(view==Details){

            Details.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
            course.setBackgroundTintList(getResources().getColorStateList(R.color.white));
            Details.setTextColor(getResources().getColorStateList(R.color.white));
            course.setTextColor(getResources().getColorStateList(R.color.black));

            changeFragment(new CoursesDetailFragment());

        }




        if(view==li_course_amount){


           // startPayment();

        }
    }

    public void startPayment( ) {

        SharedPreferences myPreferences =   getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);

      //  blink_animation.setText(""+balanceCheckResponse.getName() +"\n"+balanceCheckResponse.getUsername());




        int   amount_toPay = Integer.parseInt(finalpay.trim().replace("Rs.  ",""));

        final Activity activity = this;
        final Checkout co = new Checkout();

        float amountRupees = Float.valueOf(amount_toPay)*100;


        try {
            JSONObject options = new JSONObject();
            options.put("name", ""+getResources().getString(R.string.app_name));
             options.put("description", " "+packagename+"  ||  "+ "  "+getResources().getString(R.string.app_name));
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", amountRupees);
            JSONObject preFill = new JSONObject();
            preFill.put("email", ""+balanceCheckResponse.getUsername());
            preFill.put("contact", ""+balanceCheckResponse.getContact());
            options.put("prefill", preFill);
            co.open(activity, options);

        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {

        try {

            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

        }

        HitApiSusses(razorpayPaymentID);

    }

    @Override
    public void onPaymentError(int i, String s) {


    }

    private void HitApiSusses(String razorpayPaymentID) {


        if (UtilMethods.INSTANCE.isNetworkAvialable(CouressDetailPAyActivity.this)) {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            UtilMethods.INSTANCE.productpaid(CouressDetailPAyActivity.this,""+ finalpay.toString().trim(),
                    razorpayPaymentID,CouressId,loader,this ,"package");


        } else {
            UtilMethods.INSTANCE.Error(CouressDetailPAyActivity.this,
                    getResources().getString(R.string.network_error_message),0);
        }


    }


}