package co.civilguruji.Jaihindlms.Splash.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import co.civilguruji.Jaihindlms.Login.ui.LoginActivity;
import co.civilguruji.Jaihindlms.R;

public class AccountSelectActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout type_A_layout,type_B_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_select);


        GetID();


    }

    private void GetID() {

        type_B_layout=findViewById(R.id.type_B_layout);
        type_A_layout=findViewById(R.id.type_A_layout);

        type_A_layout.setOnClickListener(this);
        type_B_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==type_A_layout){


            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        if(view==type_B_layout){

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


    }
}