package co.civilguruji.Jaihindlms.Login.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import co.civilguruji.Jaihindlms.Activity.SignupAllActivity;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

     Loader loader;
     EditText etMobileNo,etPassword;
     TextView login,tvSignup;
     ImageView showpassword;
     LinearLayout google_login,forgot_password;
     private int RC_SIGN_IN = 427;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==427){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }

    //Relate to google login

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                String id=account.getId();
                String f_name=account.getGivenName();
                String l_name=account.getFamilyName();
                String Email_st=account.getEmail();

                Log.e("signInWithCredential","signInWithCredential: " + "name " +f_name + "email " + id +
                        " uid " + id+ " l_name " + l_name +""+Email_st);

                if (UtilMethods.INSTANCE.isNetworkAvialable(LoginActivity.this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);
                    UtilMethods.INSTANCE.sociallogin(LoginActivity.this,""+Email_st, ""+f_name, loader,this);




                } else {
                    UtilMethods.INSTANCE.Error(LoginActivity.this,
                            getResources().getString(R.string.network_error_message),0);
                }


            }
        } catch (ApiException e) {
            Log.w("Error message", "signInResult:failed code=" + e.getStatusCode());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google_login=findViewById(R.id.google_login);
        google_login.setOnClickListener(this);

        forgot_password=findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(this);

        showpassword=findViewById(R.id.showpassword);
        showpassword.setOnClickListener(this);

        tvSignup=findViewById(R.id.tvSignup);

        login=findViewById(R.id.login);
        login.setOnClickListener(this);

        tvSignup.setOnClickListener(this);

        etMobileNo=findViewById(R.id.etMobileNo);
        etPassword=findViewById(R.id.etPassword);

        loader = new Loader(this,android.R.style.Theme_Translucent_NoTitleBar);

    }

    int passcount=0;
    GoogleSignInClient mGoogleSignInClient;

    public void Sign_in_with_gmail(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



     /*   GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();*/

      //  mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);

        Log.e("fnameacc",""+  ""+ account);

        if (account != null) {

            String id=account.getId();
            String f_name=account.getGivenName();
            String l_name=account.getFamilyName();
            String G_Email=account.getEmail();

   Log.e("fname"," As :   "+  "  "+ id + "  ,  f name "+ f_name +"  ,  name "+ l_name  +"   ,   G_Email  :   "+ G_Email);

  // Toast.makeText(LoginActivity.this, ""+ id + "  f name "+ f_name +"  name "+ l_name, Toast.LENGTH_SHORT).show();

            if (UtilMethods.INSTANCE.isNetworkAvialable(LoginActivity.this)) {

                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);

                UtilMethods.INSTANCE.sociallogin(LoginActivity.this,""+G_Email, ""+f_name, loader,this);

            } else {

                UtilMethods.INSTANCE.Error(LoginActivity.this,
                        getResources().getString(R.string.network_error_message),0);

                         }
        }
        else {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, 1234);
        }

    }


    public  EditText  edMobileFwp;
     public  Button  FwdokButton,cancelButton;



    public void OpenDialogFwd() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.forgotpass, null);

        edMobileFwp = (EditText) view.findViewById(R.id.etMobileNo);
         FwdokButton = (Button) view.findViewById(R.id.okButton);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);

        final Dialog dialog = new Dialog(this);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edMobileFwp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!validateMobileFwp()) {
                    return;
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        FwdokButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateMobileFwp()) {
                    return;
                }

                if (UtilMethods.INSTANCE.isNetworkAvialable(LoginActivity.this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.ForgetPassword(LoginActivity.this, edMobileFwp.getText().toString().trim(),
                            loader,dialog);


                } else {
                    UtilMethods.INSTANCE.NetworkError(LoginActivity.this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public boolean validateMobileFwp(){
        if (edMobileFwp.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();

            return false;
        } else {

         }

        return true;
    }



    @Override
    public void onClick(View view) {

        if(view==forgot_password){


            OpenDialogFwd();

        }

 if(view==google_login){

     Intent signInIntent = mGoogleSignInClient.getSignInIntent();
     startActivityForResult(signInIntent, RC_SIGN_IN);


         //   Sign_in_with_gmail();


        }


        if(view==showpassword){


            if(passcount==0){

                passcount=1;
                etPassword.setTransformationMethod(null);

                showpassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye));


            } else {
                passcount=0;
                 etPassword.setTransformationMethod(new PasswordTransformationMethod());

                showpassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_invisible));

            }
        }


   if(view==tvSignup){


         //   Intent intent = new Intent(this, SignupActivity.class);
            Intent intent = new Intent(this, SignupAllActivity.class);
            startActivity(intent);
            finish();


        }


        if(view==login){

           /* Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();*/

            if(etPassword.getText().toString().trim().isEmpty()) {

                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();

            }else if(!UtilMethods.INSTANCE.isValidEmail(etMobileNo.getText().toString().trim())) {

                Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();

            }else {


                if (UtilMethods.INSTANCE.isNetworkAvialable(LoginActivity.this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.secureLogin(LoginActivity.this, etMobileNo.getText().toString().trim(),
                            etPassword.getText().toString().trim() ,loader,this);

                } else {
                    UtilMethods.INSTANCE.NetworkError(LoginActivity.this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }

            }

        }

    }


}