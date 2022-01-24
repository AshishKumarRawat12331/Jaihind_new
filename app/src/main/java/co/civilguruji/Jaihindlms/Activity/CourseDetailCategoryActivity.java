package co.civilguruji.Jaihindlms.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ViewDetailCourseAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.DataUser;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class CourseDetailCategoryActivity extends AppCompatActivity implements View.OnClickListener ,
        PaymentResultListener {

    String CourseId;
    String packageid;
    String packagename;
    String amount_pk;
    RecyclerView recycler_categoryblog_detail;
    Loader loader;
    TextView Enrollnow,amount;
    LinearLayout bottem_en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_category);

        CourseId=getIntent().getStringExtra("CourseId");
        amount_pk=getIntent().getStringExtra("amount");
        packageid=getIntent().getStringExtra("packageid");
        packagename=getIntent().getStringExtra("packagename");

        bottem_en=findViewById(R.id.bottem_en);
        amount=findViewById(R.id.amount);
        amount.setText("Rs. "+amount_pk );


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
        Enrollnow=findViewById(R.id.Enrollnow);
        Enrollnow.setOnClickListener(this);



        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {


            loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            UtilMethods.INSTANCE.packagecourse(this,recycler_categoryblog_detail,CourseId,loader ,
                    amount_pk,"2",packagename,packageid);


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

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
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


        if (UtilMethods.INSTANCE.isNetworkAvialable(CourseDetailCategoryActivity.this)) {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            UtilMethods.INSTANCE.productpaid(CourseDetailCategoryActivity.this,""+ amount_pk.toString().trim(),
                    razorpayPaymentID,packageid,loader,this,"package");


        } else {
            UtilMethods.INSTANCE.Error(CourseDetailCategoryActivity.this,
                    getResources().getString(R.string.network_error_message),0);
        }


    }

    public void startPayment( ) {

        SharedPreferences myPreferences =   getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);

        int   amount_toPay = Integer.parseInt(amount_pk.trim().replace("Rs.  ",""));
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



    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {



        if (activityFragmentMessage.getFrom().equalsIgnoreCase("Failure")) {

            startPayment();

        }

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("Successful")) {



        }






    }



    @Override
    public void onClick(View view) {

        if(view==Enrollnow){


         //   HitApiSusses("uuu6756ujj7");



            if(amount_pk.toString().trim().equalsIgnoreCase("0")){

                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.Productgetfree(this,  ""+ packageid ,loader,
                            this,"package");

                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }


            }else {

                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.BuyCheck(this,  ""+ packageid ,loader,this);

                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }


            }






        }

    }
}