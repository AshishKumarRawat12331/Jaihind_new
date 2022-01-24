package co.civilguruji.Jaihindlms.Dashboard.DashboadNew;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.rampo.updatechecker.UpdateChecker;

import co.civilguruji.Jaihindlms.Activity.ChangePasswordActivity;
import co.civilguruji.Jaihindlms.Activity.ContactUsActivity;
import co.civilguruji.Jaihindlms.Activity.EnrollGetActivity;
import co.civilguruji.Jaihindlms.Activity.ProfileActivity;
import co.civilguruji.Jaihindlms.Activity.SearchActivityCourse;
import co.civilguruji.Jaihindlms.Activity.WebViewActivity;
import co.civilguruji.Jaihindlms.ApiRespose.DataUser;
import co.civilguruji.Jaihindlms.Dashboard.ui.ServiceFragment;
import co.civilguruji.Jaihindlms.Fragment.AskaQuestionFragment;
import co.civilguruji.Jaihindlms.Fragment.BlogFragment;
import co.civilguruji.Jaihindlms.Fragment.CoursesFragment;
import co.civilguruji.Jaihindlms.Fragment.LIveSessionFragment;
import co.civilguruji.Jaihindlms.Fragment.NotificationFragment;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.GooglePlayStoreAppVersionNameLoader;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , BottomNavigationView.OnNavigationItemSelectedListener {

    Loader loader;
    FrameLayout main_container;
    String version="";
    String versionName="";
    int versionCode;

    LinearLayout add_post;
    TextView city_name;
    ImageView heart_im;
    TextView select_text;
    private Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    RelativeLayout rlSideList;
    AppCompatTextView Logout,Profile,Change_Password,my_course;
    TextView name,emailid,phone,tv_About;
    ImageView im_notifications,im_search,im_contect_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard_new);

        im_notifications=findViewById(R.id.im_notifications);
        im_search=findViewById(R.id.im_search);
        im_contect_us=findViewById(R.id.im_contect_us);

        name=findViewById(R.id.name);
        emailid=findViewById(R.id.emailid);
        phone=findViewById(R.id.phone);
        tv_About=findViewById(R.id.tv_About);
        Logout=findViewById(R.id.Logout);
        Profile=findViewById(R.id.Profile);
        Change_Password=findViewById(R.id.Change_Password);
        my_course=findViewById(R.id.my_course);


        Logout.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Change_Password.setOnClickListener(this);
        my_course.setOnClickListener(this);
        im_notifications.setOnClickListener(this);
        im_search.setOnClickListener(this);
        im_contect_us.setOnClickListener(this);
        tv_About.setOnClickListener(this);

        rlSideList=(RelativeLayout)findViewById(R.id.side_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        city_name=findViewById(R.id.city_name);
        heart_im=findViewById(R.id.heart_im);
        select_text=findViewById(R.id.select_text);

        add_post=findViewById(R.id.add_post);
        add_post.setOnClickListener(this);
        heart_im.setOnClickListener(this);




        loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);
        main_container = (FrameLayout) findViewById(R.id.main_container);
        version= GooglePlayStoreAppVersionNameLoader.newVersion;

        getVersionInfo();

       PopUpdate();

        initToolbar();
        SetValue();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.Courses).setChecked(true);

        select_text.setText("Live Session");
        select_text.setText("Courses");

        loadFragment(new CoursesFragment());

      //  loadFragment(new LIveSessionFragment());

        final SharedPreferences sp = getSharedPreferences("Token", MODE_PRIVATE);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();


                        sp.edit().putString("token", token).apply();
                        Log.e("TAG", "TOKEN: "+token );
                    }
                });


        String device_token=""+sp.getString("token", "");

        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

            UtilMethods.INSTANCE.notification(this,device_token,loader);

        }







    }

    private void SetValue() {


    // TextView name,emailid,phone;


        SharedPreferences myPreferences =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);

        name .setText(""+balanceCheckResponse.getName());
        emailid.setText(""+balanceCheckResponse.getEmail());
        phone.setVisibility(View.INVISIBLE);


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

    private  void PopUpdate() {

        Log.e("version","    versionName    "+versionName +"  version    "+version );

        if(version!=null && !version.equalsIgnoreCase("")){

            if(!versionName.equalsIgnoreCase(version)){

                OpenUpdateDialog();

            }

        }
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(rlSideList);
    }

    public void openDrawer() {

        mDrawerLayout.openDrawer(rlSideList);

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
                goToMarket( MainActivity.this);
                dialog.dismiss();
                //UtilMethods.INSTANCE.goAnotherActivity((Activity) context,Splash.class);
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

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        if(view==im_notifications){

            new NotificationFragment().show(getSupportFragmentManager(), "Notification");


        }
     if(view==im_search){

         startActivity(new Intent(MainActivity.this, SearchActivityCourse.class));


        }

 if(view==my_course){


         startActivity(new Intent(MainActivity.this, EnrollGetActivity.class));


        }

if(view==im_contect_us){

         startActivity(new Intent(MainActivity.this, ContactUsActivity.class));


        }


         if(view==Logout){

             LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             View viewpop = inflater.inflate(R.layout.logout_pop, null);

             Button okButton = (Button) viewpop.findViewById(R.id.okButton);
             Button Cancel = (Button) viewpop.findViewById(R.id.Cancel);
             TextView msg = (TextView) viewpop.findViewById(R.id.msg);

             final Dialog dialog = new Dialog(MainActivity.this);

             dialog.setCancelable(false);
             dialog.setContentView(viewpop);
             dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

             okButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     UtilMethods.INSTANCE.logout(MainActivity.this);

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

             closeDrawer();

        }


       if(view==Profile){

           startActivity(new Intent(MainActivity.this, ProfileActivity.class));

           closeDrawer();

        }


         if(view==tv_About){

             Intent i=new Intent(MainActivity.this,WebViewActivity.class);
             i.putExtra("name","About Us");
             i.putExtra("url","");
             startActivity(i);

             closeDrawer();


        }


         if(view==Change_Password){

             startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));

             closeDrawer();


        }

         if(view==heart_im){




        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.Special_update:

                select_text.setText("Special Update");
                fragment = new ServiceFragment();

                break;

            case R.id.free_Courses:

                select_text.setText("Live Session");
                fragment = new LIveSessionFragment();

                break;

            case R.id.Courses:

                select_text.setText("Courses");
                fragment = new CoursesFragment();

                break;

           case R.id.Ask_Question:

               select_text.setText("Free Video");
              // fragment = new QuestionSessionFragment();
               fragment = new AskaQuestionFragment();

               break;

           case R.id.Blog:

               select_text.setText("Blog knowlage");
               fragment = new BlogFragment();

               break;



        }


        return loadFragment(fragment);


    }
}


