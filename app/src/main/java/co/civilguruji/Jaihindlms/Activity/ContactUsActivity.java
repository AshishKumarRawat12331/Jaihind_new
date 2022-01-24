package co.civilguruji.Jaihindlms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import Jaihindlms.R;
import co.civilguruji.Jaihindlms.ApiRespose.DataLogin;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name,massage_user;
    TextView tv_Submit;
    Loader loader;
    String user_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);


        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Contact Us");
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

        et_name=findViewById(R.id.et_name);
        massage_user=findViewById(R.id.massage_user);
        tv_Submit=findViewById(R.id.tv_Submit);

        tv_Submit.setOnClickListener(this);


        SharedPreferences myPreferences =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataLogin balanceCheckResponse = new Gson().fromJson(response, DataLogin.class);
        et_name.setText(""+ balanceCheckResponse.getName());

    }

    @Override
    public void onClick(View view) {


        if(view==tv_Submit){

            if(massage_user.getText().toString().trim().isEmpty()) {

                Toast.makeText(this, "Enter Message", Toast.LENGTH_SHORT).show();

            }  else {

                if (UtilMethods.INSTANCE.isNetworkAvialable( this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.ContactUs(this,  ""+et_name.getText().toString().trim()
                            ,""+massage_user.getText().toString().trim(),loader,this,massage_user );

                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }

            }


        }

    }
}