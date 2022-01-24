package co.civilguruji.Jaihindlms.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import co.civilguruji.Jaihindlms.ApiRespose.DataUser;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;

public class ProfileActivity extends AppCompatActivity  implements View.OnClickListener {

 TextView name,mobile, email;
 ImageView editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Getid();

    }

    private void Getid() {

        editprofile=findViewById(R.id.editprofile);
        editprofile.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);


        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        name=findViewById(R.id.name);

        SetValue();

    }

    private void SetValue() {

        SharedPreferences myPreferences =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");

        Log.e("responsesss","As  : "+ response);

        if(!response.equalsIgnoreCase("") && response!=null &&
                !response.equalsIgnoreCase("null")) {

            DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
            name .setText(""+balanceCheckResponse.getName());
            email.setText(""+balanceCheckResponse.getUsername());
            mobile.setText(""+balanceCheckResponse.getContact());

        }

    }

    @Override
    public void onClick(View view) {

        if(view==editprofile){


            startActivity(new Intent(this,EditProfileuserlActivity.class));



        }
    }
}