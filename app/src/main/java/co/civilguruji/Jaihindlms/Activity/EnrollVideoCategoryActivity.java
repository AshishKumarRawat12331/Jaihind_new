package co.civilguruji.Jaihindlms.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.PartVideoAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.Channelsub;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class EnrollVideoCategoryActivity extends AppCompatActivity {

    String enroll_id;
    String type;
    String amount_get;
    RecyclerView recycler_categoryblog_detail;
    Loader loader;
    LinearLayout bottem_en;
    TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_blog_category);

        bottem_en=findViewById(R.id.bottem_en);

        enroll_id=getIntent().getStringExtra("enroll_id");
        type=getIntent().getStringExtra("type");
        amount_get=getIntent().getStringExtra("amount");

        amount=findViewById(R.id.amount);
        amount.setText("Rs.  "+amount_get );

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

        Getid();


        if(type.equalsIgnoreCase("get")){

            bottem_en.setVisibility(View.GONE);

        }else {


        }

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

        UtilMethods.INSTANCE.postsuserbuy(this, enroll_id,loader);

    }

    PartVideoAdapter mAdapter;
    ArrayList<PartsChannelVideo> transactionsObjects = new ArrayList<>();
    Channelsub transactions = new Channelsub();
    LinearLayoutManager mLayoutManager;

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                Log.e("query",query);

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("query",newText);
                //  mAdapter.filter(newText);
                newText=newText.toLowerCase();

                ArrayList<PartsChannelVideo>newlist=new ArrayList<>();
                for(PartsChannelVideo op:transactionsObjects)
                {
                    String getName=op.getTitle().toString().trim().toLowerCase();

                    if(getName.contains(newText)){
                        newlist.add(op);
                    }

                }
                mAdapter.filter(newlist);
                return true;

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }




    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("postsuserbuy")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }

    public void dataParse(String response) {

        Gson gson = new Gson();
        transactions = gson.fromJson(response, Channelsub.class);
        transactionsObjects = transactions.getParts();

        if (transactionsObjects.size() > 0) {
            mAdapter = new PartVideoAdapter(transactionsObjects, this);
            recycler_categoryblog_detail.setLayoutManager(new GridLayoutManager(this, 1,LinearLayoutManager.VERTICAL, false));
            recycler_categoryblog_detail.setItemAnimator(new DefaultItemAnimator());
            recycler_categoryblog_detail.setAdapter(mAdapter);
            recycler_categoryblog_detail.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();
            recycler_categoryblog_detail.setVisibility(View.VISIBLE);

        } else {
            recycler_categoryblog_detail.setVisibility(View.GONE);

            UtilMethods.INSTANCE.Error(this,"Empty Value",0);


        }

    }

}