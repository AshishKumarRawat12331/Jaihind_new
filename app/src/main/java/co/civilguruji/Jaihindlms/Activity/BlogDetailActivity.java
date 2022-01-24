package co.civilguruji.Jaihindlms.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import Jaihindlms.R;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;

public class BlogDetailActivity extends AppCompatActivity {

    public TextView title  ;
    String position;
    WebView webview;
    ImageView recent_post_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        GetId();


    }

    private void GetId() {

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("");
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        webview=findViewById(R.id.webview);
        position=getIntent().getStringExtra("position");
        title =  (TextView) findViewById(R.id.title);
        recent_post_image =   findViewById(R.id.recent_post_image);

        Gson gson = new Gson();
        SetChannelVideo operator = gson.fromJson(position, SetChannelVideo.class);

        String htmlAsString = operator.getContent().toString().trim();  // used by WebView

        title.setText(""+operator.getTitle());

        webview.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);


            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.rnd_logo);
            requestOptions.error(R.drawable.rnd_logo);




            Glide.with(this)
                     .load(ApplicationConstant.INSTANCE.ImageBAseUrl +operator.getImage())
                     .apply(requestOptions)
                    .into(recent_post_image);



    }

}