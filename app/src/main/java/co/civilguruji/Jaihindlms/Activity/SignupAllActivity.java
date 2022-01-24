package co.civilguruji.Jaihindlms.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.List;

import co.civilguruji.Jaihindlms.Login.ui.LoginActivity;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class SignupAllActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_fullname,Username ,ed_email,contact , ed_Password, ed_Confirm_password  ;
    TextView tv_signup,tvSignin;
    ImageView imgUser;
    public static final int REQ_PICK_IMAGE = 4569;
    Loader loader;
    ImageView showpassword,conformshowpassword;

    File fileUri1;
    private static final int REQ_CAPTURE_IMAGE_FIRST = 1001;
    private static final int REQ_CROP_IMAGE_FIRST = 1007;
    String imagepath="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_all);

        conformshowpassword=findViewById(R.id.conformshowpassword);
        showpassword=findViewById(R.id.showpassword);




        conformshowpassword.setOnClickListener(this);
        showpassword.setOnClickListener(this);

        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("Sign In");
       toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        GetId();


    }

    private void GetId() {

        imgUser=findViewById(R.id.imgUser);

        tv_signup=findViewById(R.id.tv_signup);
        tvSignin=findViewById(R.id.tvSignin);
        tv_signup.setOnClickListener(this);
        tvSignin.setOnClickListener(this);
        imgUser.setOnClickListener(this);

        et_fullname=findViewById(R.id.et_name);
        Username=findViewById(R.id.Username);
        ed_email=findViewById(R.id.ed_email);
        contact=findViewById(R.id.Username);
        ed_Password=findViewById(R.id.ed_Password);
        ed_Confirm_password=findViewById(R.id.ed_Confirm_password);

    }

    int passcount=0;
    int conf_passcount=0;

    @Override
    public void onClick(View view) {


        if(view==showpassword){


            if(passcount==0){

                passcount=1;
                ed_Password.setTransformationMethod(null);
                showpassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye));


            } else {
                passcount=0;
                ed_Password.setTransformationMethod(new PasswordTransformationMethod());
                showpassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_invisible));

            }

        }

      if(view==conformshowpassword){

          if(conf_passcount==0){

              conf_passcount=1;
              ed_Confirm_password.setTransformationMethod(null);
              conformshowpassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye));


          } else {
              conf_passcount=0;
              ed_Confirm_password.setTransformationMethod(new PasswordTransformationMethod());
              conformshowpassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_invisible));

          }
        }

        if(view==tvSignin){

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

  if(view==imgUser){

            chekPermision();

        }

        if(view==tv_signup){


           if(!ed_Password.getText().toString().trim().equalsIgnoreCase(ed_Confirm_password.getText().toString().trim())) {

                Toast.makeText(this, "Enter Password and Check", Toast.LENGTH_SHORT).show();

            }else if(et_fullname.getText().toString().trim().isEmpty()) {

                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();

            } else if(Username.getText().toString().trim().isEmpty()) {

                Toast.makeText(this, "Enter User Name", Toast.LENGTH_SHORT).show();

            }else if(contact.getText().toString().trim().isEmpty()) {

                Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();

            }else if(!UtilMethods.INSTANCE.isValidEmail(ed_email.getText().toString().trim())) {

                Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();

            }else {

               if (UtilMethods.INSTANCE.isNetworkAvialable( this)) {

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.signup(this, contact.getText().toString().trim(), ""+et_fullname.getText().toString().trim()
                             ,""+Username.getText().toString().trim(),""+ed_email.getText().toString().trim(),""+ed_Password.getText().toString().trim(),
                            "2","2",loader,  this,imagepath);

                } else {
                    UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message));
                }

            }

        }

    }


    private void chekPermision() {
        Dexter.withActivity(this).withPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                if (report.areAllPermissionsGranted()) {
//                    showPictureChooser();

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);

                    try {
                        startActivityForResult(Intent.createChooser(intent,
                                "Select"), REQ_PICK_IMAGE);

                    } catch (Exception ex) {
                        showToast("Please install a File Manager.",
                                Toast.LENGTH_SHORT);
                    }

                } else {
                    showAlertDialog();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                 token.continuePermissionRequest();
            }
        }).check();
    }

    Toast toast;

    public void showToast(final String text, final int duration) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }


    private void showAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Important");
        alertDialogBuilder.setMessage(this.getResources().getString(R.string.permission_msg));
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CAPTURE_IMAGE_FIRST) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    if (fileUri1 == null || !fileUri1.exists()) {
                        Uri tmp_fileUri = data.getData();
                         // Debug.e("", "fileUri : " + fileUri.getPath());

                        String selectedImagePath = UriHelper.getPath(
                                this, tmp_fileUri);
                        fileUri1 = new File(selectedImagePath);

                    } else {

                    }

                    if (fileUri1 != null && fileUri1.exists()) {
                        if (UtilMethods.INSTANCE.isJPEGorPNG(fileUri1.getAbsolutePath())) {
                            startCropActivity(Uri.fromFile(fileUri1));
                        } else {
                            showToast("Select PNG or JPEG file only", Toast.LENGTH_SHORT);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } else if (requestCode == REQ_PICK_IMAGE) {

            if (resultCode == Activity.RESULT_OK) {

                Uri tmp_fileUri = data.getData();

                String selectedImagePath = UriHelper.getPath( this,
                        tmp_fileUri);
                fileUri1 = new File(selectedImagePath);

                if (UtilMethods.INSTANCE.isJPEGorPNG(fileUri1.getAbsolutePath())) {
                    startCropActivity(Uri.fromFile(fileUri1));
                } else {
                    showToast("Select PNG or JPEG file only", Toast.LENGTH_SHORT);
                }

            }
        } else if (requestCode == REQ_CROP_IMAGE_FIRST) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                String selectedImagePath = UriHelper.getPath(
                        this, resultUri);
                fileUri1 = new File(selectedImagePath);

                // Toast.makeText(this, ""+fileUri1.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                imagepath=""+fileUri1.getAbsolutePath();


                File imgFile = new  File(""+imagepath);


                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                   // ImageView myImage = (ImageView) findViewById(R.id.imgUser);

                    imgUser.setImageBitmap(myBitmap);

                }


//                    llAddImage.setVisibility(View.GONE);
//                    llImage.setVisibility(View.VISIBLE);
                //   imageLoader.displayImage("file://" + fileUri1.getAbsolutePath(), imgUser);


                imgUser.setTag(fileUri1);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void startCropActivity(Uri tmp_fileUri) {
        Intent intent = CropImage.activity(tmp_fileUri).setGuidelines(CropImageView.Guidelines.OFF).setAllowRotation(true).setFixAspectRatio(true).getIntent(this);
        startActivityForResult(intent, REQ_CROP_IMAGE_FIRST);
    }
}