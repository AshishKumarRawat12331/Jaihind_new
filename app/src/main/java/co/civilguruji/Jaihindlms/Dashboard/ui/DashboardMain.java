package co.civilguruji.Jaihindlms.Dashboard.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.rampo.updatechecker.UpdateChecker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import co.civilguruji.Jaihindlms.ApiRespose.DataLogin;
import co.civilguruji.Jaihindlms.Fragment.LectureFragment;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.GooglePlayStoreAppVersionNameLoader;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;
import de.hdodenhof.circleimageview.CircleImageView;

public class  DashboardMain extends BaseActivity implements
        View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    DrawerLayout mDrawerLayout;
    RelativeLayout rlSideList;
    FrameLayout main_container;
    private static long back_pressed;
    public static int countBackstack = 0;
    private static final int TIME_DELAY = 2000;
    Loader loader;
    String version="";
    private Toolbar mToolbar;
    RecyclerView recyclerview;
    String versionName="";
    int versionCode;
  //  private AdView adView;
  //  AdRequest adRequest;
    ImageView cancel_page;
    CircleImageView imageView1;
    TextView name,emailid,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        GetId();

        version= GooglePlayStoreAppVersionNameLoader.newVersion;
        getVersionInfo();

         PopUpdate();


        //    initToolbar();

        final SharedPreferences sp = getSharedPreferences("Token", MODE_PRIVATE);



        String device_token=""+sp.getString("token", "");

        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

         //   UtilMethods.INSTANCE.notification(this,device_token,loader);

        }


    }

    private  void PopUpdate(){

        Log.e("version","    versionName    "+versionName +"  version    "+version );

        if(version!=null && !version.equalsIgnoreCase("")){

            if(!versionName.equalsIgnoreCase(version)){

                OpenUpdateDialog();

            }

        }
    }

    public void OpenUpdateDialog() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.update_available_pop, null);

        TextView tvLater = (TextView) view.findViewById(R.id.tv_later);
        TextView tvOk=(TextView)view.findViewById(R.id.tv_ok);

        final Dialog dialog = new Dialog(this);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        tvLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMarket(DashboardMain.this);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private static void goToMarket(Context mContext) {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(UpdateChecker.ROOT_PLAY_STORE_DEVICE + mContext.getPackageName())));
    }
    private void getVersionInfo() {

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;

            Log.e("versionnn","   versionName   "+versionName+"   versionCode  "+  versionCode+"   version  "+  version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_dashboard, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_setting) {

           /* Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Asss");
            String app_url = "https://play.google.com/store/apps/details?id="+ BuildConfig.APPLICATION_ID;
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
            startActivity(Intent.createChooser(shareIntent, "Share via"));*/
         }

        if(id==R.id.image_view) {


        }


 if(id==R.id.notification) {

         //   new NotificationFragment().show(getSupportFragmentManager(),"Dialog");

        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("");

        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDrawer();

            }
        });

     }

    private void GetId() {



        name=findViewById(R.id.name);
        emailid=findViewById(R.id.emailid);
        phone=findViewById(R.id.phone);
        imageView1=findViewById(R.id.imageView1);



SetValue();


        cancel_page=findViewById(R.id.cancel_page);
        cancel_page.setOnClickListener(this);



        rlSideList=(RelativeLayout)findViewById(R.id.side_list);
        mDrawerLayout       = (DrawerLayout) findViewById(R.id.drawer_layout);

        loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);
        main_container = (FrameLayout) findViewById(R.id.main_container);

        SharedPreferences myPreferences =   getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String categoriesresponse = myPreferences.getString(ApplicationConstant.INSTANCE.categories, "");
        Getcategories(categoriesresponse);

        changeFragment(new LectureFragment());

    }

    private void SetValue() {


        String Email="";
        SharedPreferences myPreferences =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataLogin balanceCheckResponse = new Gson().fromJson(response, DataLogin.class);
        String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.one, "");
        Email = ""+balanceResponse;

        if ( Email.equalsIgnoreCase("1")){

            name.setText(""+balanceCheckResponse.getName());
            emailid.setText(""+balanceCheckResponse.getUsername());
            // phone.setText("+91-"+"---------");
            phone.setVisibility(View.INVISIBLE);

            phone.setVisibility(View.GONE);



            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.customer_support);
            requestOptions.error(R.drawable.noimage);

            Glide.with(this)
                    .load( balanceCheckResponse.getAvatar())
                    .apply(requestOptions)
                    .into(imageView1);




        }else{


        }


    }


    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("category_post")) {

            closeDrawer();

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    public void Getcategories(String response) {/*

        Gson gson = new Gson();
        sliderImage = gson.fromJson(response, RecentPosts.class);
        operator = sliderImage.getArchive();


        if (operator != null && operator.size() > 0) {

            mAdapter = new CategoryAdapter(operator,this);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
            recyclerview.setLayoutManager(mLayoutManager);
            recyclerview.setItemAnimator(new DefaultItemAnimator());
            recyclerview.setAdapter(mAdapter);

        } else {



        }
*/

    }

    private void changeFragment(Fragment targetFragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onBackPressed() {
      //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         if (countBackstack > 0) {
            countBackstack = 0;
//            fm.beginTransaction().replace(R.id.main_container, new LectureFragment()).commit();

        }else if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
 
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onResume() {


        super.onResume();




    }

    @Override
    public void onPause() {
         super.onPause();

    }
    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    protected void reLaunchApp(){

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {


      /*  if(view==Video){

            closeDrawer();
            startActivity(getIntent());

           *//* Intent i=new Intent(this, GalleryVideoActivity.class);
             i.putExtra("type","Video");
            startActivity(i);*//*
             finish();



  }*/


          if(view==cancel_page){

              LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              View viewpop = inflater.inflate(R.layout.logout_pop, null);

              Button okButton = (Button) viewpop.findViewById(R.id.okButton);
              Button Cancel = (Button) viewpop.findViewById(R.id.Cancel);
              TextView msg = (TextView) viewpop.findViewById(R.id.msg);

              final Dialog dialog = new Dialog(DashboardMain.this);

              dialog.setCancelable(false);
              dialog.setContentView(viewpop);
              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

              //  msg.setText("");

              okButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      UtilMethods.INSTANCE.logout(DashboardMain.this);

                      dialog.dismiss();

                  }
              });

              Cancel.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      dialog.dismiss();

                  }
              });

              dialog.show();

  }

    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(rlSideList);
    }

    public void openDrawer() {

        mDrawerLayout.openDrawer(rlSideList);
    }


}
