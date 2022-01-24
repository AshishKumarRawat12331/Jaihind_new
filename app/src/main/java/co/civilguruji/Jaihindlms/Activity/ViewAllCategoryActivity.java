package co.civilguruji.Jaihindlms.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ViewDetailCourseAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class ViewAllCategoryActivity extends AppCompatActivity {

    String responseApi;
    RecyclerView recycler_categoryblog_detail;
    Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_category);

        responseApi=getIntent().getStringExtra("responseApi");

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Courses");
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

      //  dataParse(responseApi);

        SharedPreferences myPreferences =   getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref,  MODE_PRIVATE);
        String channel_id = myPreferences.getString(ApplicationConstant.INSTANCE.CourseDetail, "");


        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {


            loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            UtilMethods.INSTANCE.View_All_categoryCouress(this,recycler_categoryblog_detail,channel_id,loader ) ;


        } else {
            UtilMethods.INSTANCE.Error(this,
                    getResources().getString(R.string.network_error_message),0);
        }

    }


    ViewDetailCourseAdapter mAdapter;
    ArrayList<SetChannelVideo> transactionsObjects = new ArrayList<>();
    ChannelVideoResponse transactions = new ChannelVideoResponse();

    public void dataParse(String response) {




        /*
       Log.e("dataParseresss",""+ response);

        Gson gson = new Gson();
        transactions = gson.fromJson(responseDetail, ChannelVideoResponse.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {
            mAdapter = new ViewDetailCourseAdapter(transactionsObjects, this);
            recycler_categoryblog_detail.setLayoutManager(new GridLayoutManager(this, 1,LinearLayoutManager.VERTICAL, false));
            recycler_categoryblog_detail.setItemAnimator(new DefaultItemAnimator());
            recycler_categoryblog_detail.setAdapter(mAdapter);
            recycler_categoryblog_detail.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();
            recycler_categoryblog_detail.setVisibility(View.VISIBLE);

        } else {
            recycler_categoryblog_detail.setVisibility(View.GONE);
        }*/




    }


}