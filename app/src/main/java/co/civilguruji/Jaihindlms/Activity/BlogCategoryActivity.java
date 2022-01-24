package co.civilguruji.Jaihindlms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import Jaihindlms.R;
import co.civilguruji.Jaihindlms.Adapter.BlogAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class BlogCategoryActivity extends AppCompatActivity {

    String blog_id,name;
    RecyclerView recycler_categoryblog_detail;
    Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_category);

        blog_id=getIntent().getStringExtra("blog_id");
        name=getIntent().getStringExtra("name");



        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(""+name);
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Getid();



    }

    private void Getid() {

        recycler_categoryblog_detail=findViewById(R.id.recycler_categoryblog_detail);
        hitApi();


    }

    private void hitApi() {

        loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.BlogCategory(this, blog_id,loader);

    }

    BlogAdapter mAdapter;
    ArrayList<SetChannelVideo> transactionsObjects = new ArrayList<>();
    ChannelVideoResponse transactions = new ChannelVideoResponse();
    LinearLayoutManager mLayoutManager;

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }


    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("BlogCategory")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }

    public void dataParse(String response) {

        Log.e("responseresponse",""+ response);

        Gson gson = new Gson();
        transactions = gson.fromJson(response, ChannelVideoResponse.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {
            mAdapter = new BlogAdapter(transactionsObjects, this);
            recycler_categoryblog_detail.setLayoutManager(new GridLayoutManager(this, 1,LinearLayoutManager.VERTICAL, false));
            recycler_categoryblog_detail.setItemAnimator(new DefaultItemAnimator());
            recycler_categoryblog_detail.setAdapter(mAdapter);
            recycler_categoryblog_detail.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();
            recycler_categoryblog_detail.setVisibility(View.VISIBLE);

        } else {
            recycler_categoryblog_detail.setVisibility(View.GONE);
        }



    }


}