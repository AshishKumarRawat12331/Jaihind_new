package co.civilguruji.Jaihindlms.Splash.ui;

 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Build;
 import android.os.Bundle;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.appcompat.app.AppCompatActivity;

 import android.view.View;
 import android.view.Window;
 import android.widget.TextView;

 import com.google.android.gms.common.ConnectionResult;
 import com.google.android.gms.common.api.GoogleApiClient;

 import co.civilguruji.Jaihindlms.Dashboard.DashboadNew.MainActivity;
 import co.civilguruji.Jaihindlms.Login.ui.LoginActivity;
 import co.civilguruji.Jaihindlms.R;
 import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
 import co.civilguruji.Jaihindlms.Utils.GooglePlayStoreAppVersionNameLoader;
 import co.civilguruji.Jaihindlms.Utils.Loader;

public class Splash extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Loader loader;
    TextView tv_version;

    @Override
    protected void onPause() {
         super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.splash_screen);

        tv_version=findViewById(R.id.tv_version);
       // tv_version.setText("V : "+ BuildConfig.VERSION_NAME);

        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);

        new GooglePlayStoreAppVersionNameLoader().execute();

       /* if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
          //  UtilMethods.INSTANCE.MasterDeta(this,null);


        UtilMethods.INSTANCE.Getstate(this,loader);

        } else {
            UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message));
        }*/


        loginpage();

    }

    public void loginpage() {

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    DashboardLogin();
                }
            }
        };
        timerThread.start();
    }

    public void DashboardLogin() {

      //  UtilMethods.INSTANCE.GetFunctionArea(this,"");

        String Email="";
        SharedPreferences myPreferences =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
          String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.one, "");
          Email = ""+balanceResponse;

        if ( Email.equalsIgnoreCase("1")){
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else{

            Intent intent = new Intent(Splash.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
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

}
