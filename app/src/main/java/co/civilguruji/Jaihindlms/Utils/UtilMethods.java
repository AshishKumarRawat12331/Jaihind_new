package co.civilguruji.Jaihindlms.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import co.civilguruji.Jaihindlms.Activity.EnrollGetActivity;
import co.civilguruji.Jaihindlms.Adapter.ChannelSubCatListAdapter;
import co.civilguruji.Jaihindlms.Adapter.ChannelVideoListAdapter;
import co.civilguruji.Jaihindlms.Adapter.ContectDetailListByIdAdapter;
import co.civilguruji.Jaihindlms.Adapter.CouressListByIdAdapter;
import co.civilguruji.Jaihindlms.Adapter.VideoPartsCommentAdapter;
import co.civilguruji.Jaihindlms.Adapter.VideoPartsCommentReplayAdapter;
import co.civilguruji.Jaihindlms.Adapter.ViewDetailCourseAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.DataUser;
import co.civilguruji.Jaihindlms.ApiRespose.LoginResponse;
import co.civilguruji.Jaihindlms.ApiRespose.MetasRespose;
import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.ApiRespose.Setcontents;
import co.civilguruji.Jaihindlms.Banner.VideoGalleryResponse;
import co.civilguruji.Jaihindlms.Dashboard.DashboadNew.MainActivity;
import co.civilguruji.Jaihindlms.R;
 import co.civilguruji.Jaihindlms.Splash.ui.Splash;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public enum UtilMethods {

    INSTANCE;

    public void change_password(final Context context, String old_password ,final String new_password, final Loader loader,
                                final Activity activity) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("change_password","new_password   "+ new_password + "   userid  "+ user_id  +"   "+ old_password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.change_password(user_id,old_password,new_password);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("change_passwordres", "is : " + new Gson().toJson(response.body()).toString()+"   getStatus    " );

                    if (response != null) {

                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getData().getStatus().equalsIgnoreCase("true")) {

                                UtilMethods.INSTANCE.Successfulnew(context, ""+response.body().getData().getMsg(),4,activity);

                            } else {

                                UtilMethods.INSTANCE.Error(context, ""+response.body().getData().getMsg(),0);

                            }

                        } catch (Exception ex) {
                            Log.e("secureLoginerror", ex.getMessage());
                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);

                        }
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    UtilMethods.INSTANCE.Error(context, ""+ t.getMessage(),0);


                }
            });

        } catch (Exception e) {

            e.printStackTrace();

        }
    }



    public void Banner(final Context context, final Loader loader) {

        Log.e("Bannerhit", "Banner : " );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<VideoGalleryResponse> call = git.Banner();
            call.enqueue(new Callback<VideoGalleryResponse>() {
                @Override
                public void onResponse(Call<VideoGalleryResponse> call, retrofit2.Response<VideoGalleryResponse> response) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Log.e("Bannerres", "Banner : " + new Gson().toJson(response.body()).toString());

                    if (response.body() !=null ){
                        if (response.body().getStatus().equalsIgnoreCase("1")) {

                            FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "videogallery");
                            GlobalBus.getBus().post(activityActivityMessage);
                        }
                        else
                        {

                        }
                    }

                }

                @Override
                public void onFailure(Call<VideoGalleryResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Log.e("response", "Banner "+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
         return type;
    }

    public static boolean isJPEGorPNG(String url) {
        try {
            String type = getMimeType(url);
            String ext = type.substring(type.lastIndexOf("/") + 1);
            if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

        return false;
    }

    public void NetworkError(final Context context, String title, final String message) {
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setCustomImage(AppCompatResources.getDrawable(context,R.drawable.ic_connection_lost_24dp))
                .show();
    }

    public boolean isNetworkAvialable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void locationreposeval(Context context, String locationreposeval ) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
         editor.putString(ApplicationConstant.INSTANCE.locationreposeval, locationreposeval);
        editor.commit();

    }

    public void logout(Context context) {

         UtilMethods.INSTANCE.setLoginrespose(context, "", "","");
        Intent startIntent = new Intent(context, Splash.class);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(startIntent);

    }

    public boolean isValidEmail(String email) {

        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public void Successfulnew(final Context context, final String message, final int i, final Activity activity) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.poprespose, null);

        Button okButton = (Button) view.findViewById(R.id.okButton);
        TextView msg = (TextView) view.findViewById(R.id.msg);

        final Dialog dialog = new Dialog(context);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        msg.setText(""+message);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i==1){


                    dialog.dismiss();

                }else if (i==10){

                    activity.finish();

                    Intent i=new Intent(context, EnrollGetActivity.class);
                    context.startActivity(i);



                }else if (i==2){

                    activity.finish();
                    dialog.dismiss();


                    Intent i=new Intent(context, EnrollGetActivity.class);
                    context.startActivity(i);



                } else if (i==3){

                    activity.finish();
                    dialog.dismiss();

                    Intent i=new Intent(context, EnrollGetActivity.class);
                    context.startActivity(i);


                }  else if (i==4){


                    UtilMethods.INSTANCE.logout(context);

                    activity.finish();
                    dialog.dismiss();


                }  else {

                    dialog.dismiss();

                }

            }
        });

        dialog.show();

        /*

        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        pDialog.setTitleText(context.getResources().getString(R.string.successful_title));
        pDialog.setContentText(message);
        pDialog.setCustomImage(AppCompatResources.getDrawable(context,R.drawable.ic_check_circle_black_24dp));
        pDialog.setCancelable(false);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                if(i==2){

                    activity.finish();

                    context.startActivity(new Intent(context, LoginActivity.class));

                    sweetAlertDialog.dismiss();

                } else   if(i==3){

                    activity.finish();

                    Intent intent = new Intent(context, FunctionalAreaActivity.class);
                    intent.putExtra("type","Functional Areas");
                    context.startActivity(intent);

                    sweetAlertDialog.dismiss();

                }else   if(i==4){

                    UtilMethods.INSTANCE.MyprofileDashBoad(context,null);


                    activity.finish();
                    sweetAlertDialog.dismiss();

                } else {
                    sweetAlertDialog.dismiss();
                }
            }
        });
        pDialog.show();*/


    }

    public void Error(final Context context,final String message, final  int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.poprespose, null);


        Button okButton = (Button) view.findViewById(R.id.okButton);
        TextView msg = (TextView) view.findViewById(R.id.msg);
        TextView   resposestatus = (TextView) view.findViewById(R.id.resposestatus);

        final Dialog dialog = new Dialog(context);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        msg.setText(""+message);
        resposestatus.setText("Failed");


        resposestatus.setTextColor(context.getResources().getColor(R.color.red));

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();


    }


    ////////////////////// APi Add ///////////////////////


    public void Productgetfree(final Context context , final String content_id, final Loader loader,
                               final Activity activity ,String content_type) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String buyer_id=""+ balanceCheckResponse.getId();

        Log.e("Productgetfree",    " , buyer_id : " + buyer_id + " , content_id : " + content_id);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.productgetfree( buyer_id ,content_id,  ApplicationConstant.INSTANCE.User_id_clg,"free",content_type);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("Productgetfreeres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {


                            if (response.body().getStatus().equalsIgnoreCase("1") ) {

                                UtilMethods.INSTANCE.Successfulnew(context,response.body().getData().getMsg()+"",3,activity);


                                //  UtilMethods.INSTANCE.myprofile(context,""+response.body().getData().getUser().getId(),""+password,loader,activity);

                            } else {

                                UtilMethods.INSTANCE.Error(context,response.body().getMessage()+"",0);


                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void BuyCheck(final Context context , final String content_id, final Loader loader, final Activity activity) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("BuyCheck",    " , buyer_id : " + user_id + " , content_id : " + content_id);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.BuyCheck( user_id ,content_id);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("BuyCheckres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {

                            if (response.body().getData().getStatus().equalsIgnoreCase("true") ) {

                                UtilMethods.INSTANCE.Successfulnew(context,response.body().getData().getMsg(),10,activity);

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("Successful" , "Successful");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {


                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("Failure"  , "Failure");
                                GlobalBus.getBus().post(activityActivityMessage);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);

                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


 public void productpaid(final Context context, String price, String transaction_id , final String content_id,
                         final Loader loader, final Activity activity,String content_type) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("productpaid",    " , buyer_id : " + user_id + " , content_id : " + content_id);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.productpaid( user_id , ApplicationConstant.INSTANCE.User_id_clg, content_id, "download", price,content_type,
                    transaction_id);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("productpaid", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {

                            if (response.body().getData().getStatus().equalsIgnoreCase("true") ) {

                                UtilMethods.INSTANCE.Successfulnew(context,response.body().getData().getMsg(),0,null);

                            } else {

                                UtilMethods.INSTANCE.Error(context,response.body().getData().getMsg(),0);


                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void secureLogin(final Context context, final String email , final String password, final Loader loader, final Activity activity) {

        Log.e("secureLogin",    " , APIkey : " + email + " , password : " + password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.secureLogin( email ,password,ApplicationConstant.INSTANCE.User_id_clg);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("secureLoginres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {


                            if (response.body().getStatus().equalsIgnoreCase("1") ) {


                                UtilMethods.INSTANCE.setLoginrespose(context, new Gson().toJson(response.body().getData().getUser()).toString(),password, "1");
                                UtilMethods.INSTANCE.myprofile(context,""+response.body().getData().getUser().getId(),""+password,loader,activity);

                            } else {

                                UtilMethods.INSTANCE.Error(context,response.body().getMessage()+"",0);


                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void sociallogin(final Context context, final String email ,String name,
                            final Loader loader,final Activity activity) {

        Log.e("sociallogin",    " , APIkey : " + email + " , name : " + name);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.sociallogin(email,"Google_Login",name,ApplicationConstant.INSTANCE.User_id_clg );
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("socialloginres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {


                            if (response.body().getStatus().equalsIgnoreCase("1") ) {


                                UtilMethods.INSTANCE.setLoginrespose(context, new Gson().toJson(response.body().getData().getUser()).toString(),"email_id"+"", "1");

                                Toast.makeText(context, "User login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                activity.finish();




                          //      UtilMethods.INSTANCE.myprofile(context,""+response.body().getData().getUser().getId(),""+email_id,loader,activity);

                            } else {

                                UtilMethods.INSTANCE.Error(context,response.body().getMessage()+"",0);


                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ForgetPassword(final Context context, final String email ,  final Loader loader,final Dialog dialog) {

        Log.e("ForgetPassword",    " , APIkey : " + email   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.ForgetPassword(email);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("ForgetPasswordres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {


                            if (response.body().getData().getStatus().equalsIgnoreCase("true") ) {

                                UtilMethods.INSTANCE.Successfulnew(context,response.body().getData().getMsg()+"",0,null);


                            } else {

                                UtilMethods.INSTANCE.Error(context,response.body().getData().getMsg()+"",0);


                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void myprofile(final Context context, final String user_id , final String password , final Loader loader, final Activity activity) {

        Log.e("secureLoginmyprofile",    " , APIkey : " + user_id + " , password : " + password);


        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.myprofile( user_id);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("secureLoginmyprofileres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {


                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                          //      UtilMethods.INSTANCE.setLoginrespose(context, new Gson().toJson(response.body().getData()).toString(),password, "1");

                                Toast.makeText(context, "user login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                activity.finish();



                            } else {

                                UtilMethods.INSTANCE.Error(context,response.body().getMessage()+"",0);


                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoginrespose(Context context, String Loginrespose ,String password,String one ) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.Loginrespose, Loginrespose);
        editor.putString(ApplicationConstant.INSTANCE.one, one);
        editor.putString(ApplicationConstant.INSTANCE.password, password);
        editor.commit();

    }

     public void Get_Course_id(Context context, String Course_id ,String response ) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.Course_id, Course_id);
        editor.putString(ApplicationConstant.INSTANCE.response, response);
         editor.commit();

    }
 public void Get_part_video_id(Context context, String Part_video_id  ) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.Part_video_id, Part_video_id);
         editor.commit();

    }


 public void Detail_content(Context context,  String content_response ) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
         editor.putString(ApplicationConstant.INSTANCE.content_response, content_response);
         editor.commit();

    }

 public void part_description(Context context,  String content_response ) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
         editor.putString(ApplicationConstant.INSTANCE.part_description, content_response);
         editor.commit();

    }

     public void GetCourseDetail(Context context,  String CourseDetail ) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
         editor.putString(ApplicationConstant.INSTANCE.CourseDetail, CourseDetail);
         editor.commit();

    }

    public void signup(final Context context,String contact, String first_name,String Username,   final String email, final String password, String state_id, String city_id, final Loader loader, final Activity loginActivity , String userimage) {

        // Map is used to multipart the file using okhttp3.RequestBody

        File fileregistration = new File(userimage);
        MultipartBody.Part fileToUpload1;

        if(userimage.equalsIgnoreCase("1")){

            fileToUpload1 = MultipartBody.Part.createFormData("image", "");

        }else {

            RequestBody requestBody1 = RequestBody.create(MediaType.parse("*image/*"), fileregistration);
            fileToUpload1 = MultipartBody.Part.createFormData("image", fileregistration.getName(), requestBody1);

        }

        RequestBody first_nameres = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody Usernameres = RequestBody.create(MediaType.parse("text/plain"), Username);
        RequestBody middle_nameres = RequestBody.create(MediaType.parse("text/plain"), "middle_name");
        RequestBody emailreq = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody passwordres = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody state_idres = RequestBody.create(MediaType.parse("text/plain"), state_id);
        RequestBody city_idres = RequestBody.create(MediaType.parse("text/plain"), city_id);

        Log.e("UserProfileupload","useridre   "+ first_name +"   last_name     "+"last_name"+"    middle_name    "+"middle_name"+"    emailreq    " +
                ""+emailreq+"    password    "+password+"     state_id   "+state_id+"     fileToUpload1      "+fileToUpload1  +"   city_id  "+ city_id +"   MobileNo  "+ "MobileNo"  );


        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.signup(first_name,Username,email,password,password
                    ,ApplicationConstant.INSTANCE.User_id_clg,contact);

            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("UserProfileuploadres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")) {


                                Toast.makeText(context, ""+response.body().getData().getDescription(), Toast.LENGTH_SHORT).show();


                                UtilMethods.INSTANCE.secureLogin(context, email+"",
                                        password+"" ,loader,loginActivity);

                            }else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getMessage(),0);

                            }

                        } catch (Exception ex) {
                            Log.e("useruploadexception", ex.getMessage());

                            UtilMethods.INSTANCE.Error(context," Exception  :  "+ ex.getMessage(),0);

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {


                    Log.e("useruploadonfali","userupload    "+ t.getMessage()  );
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {


                            }
                    }

                    UtilMethods.INSTANCE.Error(context,"Failure "+ t.getMessage(),0);


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Editprofile(final Context context,String contact, String name ,final String email,
                            final Loader loader, final Activity loginActivity ) {


        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        final String passwordVal = myPreferences.getString(ApplicationConstant.INSTANCE.password, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        final String user_id=""+ balanceCheckResponse.getId();


        Log.e("Editprofile","useridre   " +"   last_name     "+"last_name"+"    middle_name    "+"middle_name"+"    emailreq    " );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.Editprofile(  name,email,contact,ApplicationConstant.INSTANCE.User_id_clg,user_id);



            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("Editprofileres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {

                            if (response.body().getStatus().equalsIgnoreCase("true")) {



                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                UtilMethods.INSTANCE.secureLogin(context, email+"",
                                         passwordVal+"" ,loader,loginActivity);



                            }else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getMsg(),0);

                            }

                        } catch (Exception ex) {
                            Log.e("useruploadexception", ex.getMessage());

                            UtilMethods.INSTANCE.Error(context," Exception  :  "+ ex.getMessage(),0);

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {


                    Log.e("useruploadonfali","userupload    "+ t.getMessage()  );
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {


                            }
                    }

                    UtilMethods.INSTANCE.Error(context,"Failure "+ t.getMessage(),0);


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notification(final Context context ,String token, final Loader loader  ) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("tokentoken",    " , token : " +token );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.notification(ApplicationConstant.INSTANCE.User_id_clg,user_id, token);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("tokentokenres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                        try {

                            if (response.body() != null) {





                            }else {


                            }

                        } catch (Exception ex) {


                            //  UtilMethods.INSTANCE.Error(context, "Wrong Credentials User." +ex.getMessage(),0);
                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }



                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ContactUs(final Context context, String name, String message  , final Loader loader, final Activity activity, final EditText massage_user) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();


        Log.e("contactUs","user_id   "+ user_id +"   name   "+ name +"   message     "  + message);


        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.contactUs(ApplicationConstant.INSTANCE.User_id_clg ,user_id , name,message );

            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("contactUsres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {

                            if (response.body().getStatus().equalsIgnoreCase("true")) {

                                Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                massage_user.setText("");

                            }else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getMessage(),0);

                            }

                        } catch (Exception ex) {
                            Log.e("useruploadexception", ex.getMessage());

                            UtilMethods.INSTANCE.Error(context," Exception  :  "+ ex.getMessage(),0);

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {


                    Log.e("useruploadonfali","userupload    "+ t.getMessage()  );
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {


                            }
                    }

                    UtilMethods.INSTANCE.Error(context,"Failure "+ t.getMessage(),0);


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Course_cat(final Context context,  final Loader loader  ) {

        Log.e("Course_cat",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.Course_cat();
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("Course_catres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "getcouress");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getpackage(final Context context,  final Loader loader  ) {

        Log.e("getpackage",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<PackageRespose> call = git.getpackage( ApplicationConstant.INSTANCE.User_id_clg);
            call.enqueue(new Callback<PackageRespose>() {

                @Override
                public void onResponse(Call<PackageRespose> call, final retrofit2.Response<PackageRespose> response) {

                    Log.e("getpackageres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "getpackage");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<PackageRespose> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void packagecourse(final Context context, final RecyclerView recyclerchannel_video,
                              String course_id, final Loader loader, final String amount_pk,
                              final String getinroll , final String packagename , final String CouressId) {

        Log.e("getpackage",    " , course_id : "  + course_id );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.packagecourse(course_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("getpackageres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {


                                String Apiresponse="" + new Gson().toJson(response.body()).toString();


                                Gson gson = new Gson();
                                transactions = gson.fromJson(Apiresponse, ChannelVideoResponse.class);
                                transactionsObjects = transactions.getData();

                                if (transactionsObjects.size() > 0) {
                                    ViewmAdapter = new ViewDetailCourseAdapter(transactionsObjects, context ,amount_pk
                                            ,getinroll,packagename,CouressId);

                                    mLayoutManager = new LinearLayoutManager(context);
                                 // recyclerchannel_video.setLayoutManager(mLayoutManager);
                                    recyclerchannel_video.setLayoutManager(new GridLayoutManager(context, 2,LinearLayoutManager.VERTICAL, false));
                                 // recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(ViewmAdapter);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);

                                } else {

                                    recyclerchannel_video.setVisibility(View.GONE);

                                }




                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            Toast.makeText(context, "No courses available", Toast.LENGTH_SHORT).show();


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "No courses available", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


      public void PackagecourseCount(final Context context, String course_id, final Loader loader, final TextView count_couress ) {

        Log.e("getpackage",    " , course_id : "  + course_id );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.packagecourse(course_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("getpackageres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                count_couress.setText(""+ response.body().getData().size()+"  Courses");

                            } else {

                                count_couress.setText("0 courses");

                            }

                        } catch (Exception ex) {



                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }



                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void ProductComment(final Context context,  String comment, String course_id, final Loader loader ) {


        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();
        String Name_id=""+ balanceCheckResponse.getName();
        String email_id=""+ balanceCheckResponse.getUsername();

        Log.e("getpackage",    " , course_id : "  + course_id );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.productComment(user_id,comment,Name_id,email_id,course_id,"part");
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("getpackageres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {


                                Toast.makeText(context, ""+response.body().getData().getMsg(), Toast.LENGTH_SHORT).show();

                                FragmentActivityMessage activityActivityMessage =
                                         new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "productComment");
                                GlobalBus.getBus().post(activityActivityMessage);



                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                          //  Toast.makeText(context, "No courses available", Toast.LENGTH_SHORT).show();


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }



                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void contentuserbuy(final Context context,  final Loader loader  ) {


        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("Course_cat",    " , user_id : " +user_id  );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.contentuserbuy(user_id,"package");
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("Course_catres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "contentuserbuy");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Log.e("getMessage",""+ t.getMessage());

                    Toast.makeText(context, "Some technical issues!" +t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void packageuserbuy(final Context context,  final Loader loader  ) {


        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("packageuserbuy",    " , user_id : " +user_id  );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.packageuserbuy(ApplicationConstant.INSTANCE.User_id_clg,"package",user_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("packageuserbuyres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "contentuserbuy");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getMsg(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Log.e("getMessage",""+ t.getMessage());

                    Toast.makeText(context, "Some technical issues!" +t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public void Blogposts(final Context context,  final Loader loader  ) {

        Log.e("Course_cat",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.Blogposts();
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("Course_catres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "Blogposts");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BlogCategory(final Context context,String blog_id,  final Loader loader  ) {

        Log.e("BlogCategory",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.postsById(blog_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("BlogCategoryres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "BlogCategory");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void postsuserbuy(final Context context,String content_id,  final Loader loader  ) {

        Log.e("postsuserbuy",    " , user_id : "+  content_id );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ContentdetailsAll> call = git.postsuserbuy(content_id);
            call.enqueue(new Callback<ContentdetailsAll>() {

                @Override
                public void onResponse(Call<ContentdetailsAll> call, final retrofit2.Response<ContentdetailsAll> response) {

                    Log.e("postsuserbuy", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body().getData().get(0)).toString(), "postsuserbuy");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ContentdetailsAll> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Get_Notification(final Context context ,  final Loader loader  ) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();


        Log.e("postsuserbuy",    " , user_id : "+  user_id );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ContentdetailsAll> call = git.Get_Notification(user_id);
            call.enqueue(new Callback<ContentdetailsAll>() {

                @Override
                public void onResponse(Call<ContentdetailsAll> call, final retrofit2.Response<ContentdetailsAll> response) {

                    Log.e("postsuserbuy", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "get_notification");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ContentdetailsAll> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void blogcategory(final Context context,  final Loader loader  ) {

        Log.e("Course_cat",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.blogcategory();
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("Course_catres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "blogcategory");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,""+response.body().getStatus(),0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contentAllCourse(final Context context,  final Loader loader  ) {


        Log.e("getcv",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.contentAllCourse();
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("getcvres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "contentAllCourse");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Getchannel(final Context context,  final Loader loader  ) {


        Log.e("getcv",    " , user_id : "   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.channel();
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    Log.e("getcvres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body().getData()).toString(), "getchannel");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {

                                UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ChannelVideoListAdapter mAdapter;
    ChannelSubCatListAdapter mAdapterSub;
    CouressListByIdAdapter mAdapter_catbyid;
    ViewDetailCourseAdapter ViewmAdapter;
    ArrayList<SetChannelVideo> transactionsObjects = new ArrayList<>();
    ChannelVideoResponse transactions = new ChannelVideoResponse();
    LinearLayoutManager mLayoutManager;


    public void ChannelVideo(final Context context, final RecyclerView recyclerchannel_video , String channel_id, final Loader loader  ) {

        Log.e("ChannelVideo",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.channelvideos(channel_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("ChannelVideores", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();


                                Gson gson = new Gson();
                                transactions = gson.fromJson(Apiresponse, ChannelVideoResponse.class);
                                transactionsObjects = transactions.getData();



                                if (transactionsObjects.size() > 0) {
                                    mAdapter = new ChannelVideoListAdapter(transactionsObjects, context,Apiresponse );

                                  //  mLayoutManager = new GridLayoutManager(context,1);
                                   // recyclerchannel_video.setLayoutManager(mLayoutManager);

                                    recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(mAdapter);
                                    recyclerchannel_video.setNestedScrollingEnabled(false);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);
                                } else {
                                    recyclerchannel_video.setVisibility(View.GONE);
                                }


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!", Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CouressSubCat(final Context context, final RecyclerView recyclerchannel_video , String channel_id, final Loader loader  ) {

        Log.e("CouressSubCat",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.CouressSubCat(channel_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("CouressSubCatres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();


                                Gson gson = new Gson();
                                transactions = gson.fromJson(Apiresponse, ChannelVideoResponse.class);
                                transactionsObjects = transactions.getData();



                                if (transactionsObjects.size() > 0) {
                                    mAdapterSub = new ChannelSubCatListAdapter(transactionsObjects, context,Apiresponse );

                                  //  mLayoutManager = new GridLayoutManager(context,1);
                                   // recyclerchannel_video.setLayoutManager(mLayoutManager);

                                    recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(mAdapterSub);
                                    recyclerchannel_video.setNestedScrollingEnabled(false);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);
                                } else {
                                    recyclerchannel_video.setVisibility(View.GONE);
                                }


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    ContectDetailListByIdAdapter mAdapter_content;
    ArrayList<PartsChannelVideo> transactionscontent = new ArrayList<>();
    Channelsub contenttransactions = new Channelsub();



    public void contentdetails(final Context context, final RecyclerView recyclerchannel_video , String channel_id, final Loader loader  ) {

        Log.e("contentdetailsasas",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ContentdetailsAll> call = git.contentdetails(channel_id);
            call.enqueue(new Callback<ContentdetailsAll>() {

                @Override
                public void onResponse(Call<ContentdetailsAll> call, final retrofit2.Response<ContentdetailsAll> response) {

                    Log.e("contentdetailsasasres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                      /*  try {*/

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {


                                String Apiresponse="" + new Gson().toJson(response.body().getData().get(0)).toString();
                           //    String detail_COntant="" + response.body().getData().get(0).getContent().toString();
                                //    UtilMethods.INSTANCE.Detail_content(context,detail_COntant+"" );



                                Gson gson = new Gson();
                                contenttransactions = gson.fromJson(Apiresponse, Channelsub.class);
                                transactionscontent = contenttransactions.getParts();

                                if (transactionscontent.size() > 0) {
                                    mAdapter_content = new ContectDetailListByIdAdapter(transactionscontent, context,Apiresponse );
                                    recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(mAdapter_content);
                                    recyclerchannel_video.setNestedScrollingEnabled(false);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);

                                } else {

                                    recyclerchannel_video.setVisibility(View.GONE);

                                }


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                       /* } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, "Exception  "+ex.getMessage(),0);

                            Log.e("ExceptionAAAAAAAAAA","Exception :  "+ ex.getMessage());


                        }*/

                    }
                }

                @Override
                public void onFailure(Call<ContentdetailsAll> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    VideoPartsCommentAdapter mAdapter_msg;
    VideoPartsCommentReplayAdapter mAdapter_reply;
    ArrayList<MsgChannelsub> transactionsMsg = new ArrayList<>();
    ContentdetailsAll Msgtransactions = new ContentdetailsAll();



    public void GetproductComment(final Context context, final RecyclerView recyclerchannel_video ,
                                  String channel_id, final Loader loader ) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("GetproductComment",    " , channel_id : "  + channel_id  +"   ,  user_id   :  "+ user_id  );


        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ContentdetailsAll> call = git.getproductComment(channel_id);
            call.enqueue(new Callback<ContentdetailsAll>() {

                @Override
                public void onResponse(Call<ContentdetailsAll> call, final retrofit2.Response<ContentdetailsAll> response) {

                    Log.e("GetproductCommentres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }


                      /*  try {*/

                            if (response.body().getStatus().equalsIgnoreCase("true")  ) {

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();

                                Log.e("GetproductCom",""+  Apiresponse  );

                                Gson gson = new Gson();
                                Msgtransactions = gson.fromJson(Apiresponse, ContentdetailsAll.class);
                                transactionsMsg = Msgtransactions.getMsg();

                                if (transactionsMsg.size() > 0) {
                                    mAdapter_msg = new VideoPartsCommentAdapter(transactionsMsg, context,Apiresponse );
                                    recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(mAdapter_msg);
                                    recyclerchannel_video.setNestedScrollingEnabled(false);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);

                                } else {

                                    recyclerchannel_video.setVisibility(View.GONE);

                                }


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                       /* } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, "Exception  "+ex.getMessage(),0);

                            Log.e("ExceptionAAAAAAAAAA","Exception :  "+ ex.getMessage());


                        }*/

                    }
                }

                @Override
                public void onFailure(Call<ContentdetailsAll> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Get_Comment_reply(final Context context, final RecyclerView recycler_comment_reply ,
                               String channel_id, final TextView comment_view ) {

        SharedPreferences myPreferences =  context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataUser balanceCheckResponse = new Gson().fromJson(response, DataUser.class);
        String user_id=""+ balanceCheckResponse.getId();

        Log.e("GetproductComment",    " , channel_id : "  + channel_id  +"   ,  user_id   :  "+ user_id  );


        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ContentdetailsAll> call = git.productReply(channel_id);
            call.enqueue(new Callback<ContentdetailsAll>() {

                @Override
                public void onResponse(Call<ContentdetailsAll> call, final retrofit2.Response<ContentdetailsAll> response) {

                    Log.e("GetproductCommentres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {



                      /*  try {*/

                            if (response.body().getStatus().equalsIgnoreCase("true")  ) {


                                comment_view.setText(""+response.body().getReply().size() + "  Comment" );

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();
                                Gson gson = new Gson();
                                Msgtransactions = gson.fromJson(Apiresponse, ContentdetailsAll.class);
                                transactionsMsg = Msgtransactions.getReply();

                                if (transactionsMsg.size() > 0) {
                                    mAdapter_reply = new VideoPartsCommentReplayAdapter(transactionsMsg, context,Apiresponse );
                                    recycler_comment_reply.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    recycler_comment_reply.setItemAnimator(new DefaultItemAnimator());
                                    recycler_comment_reply.setAdapter(mAdapter_reply);
                                    recycler_comment_reply.setNestedScrollingEnabled(false);
                                   // recycler_comment_reply.setVisibility(View.VISIBLE);
                                } else {
                                    recycler_comment_reply.setVisibility(View.GONE);
                                }
                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                       /* } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, "Exception  "+ex.getMessage(),0);

                            Log.e("ExceptionAAAAAAAAAA","Exception :  "+ ex.getMessage());


                        }*/

                    }
                }

                @Override
                public void onFailure(Call<ContentdetailsAll> call, Throwable t) {


                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void DemoVideocontentdetails(final Context context,   String channel_id, final Loader loader  ) {

        Log.e("contentDemoVideo",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ContentdetailsAll> call = git.contentdetails(channel_id);
            call.enqueue(new Callback<ContentdetailsAll>() {

                @Override
                public void onResponse(Call<ContentdetailsAll> call, final retrofit2.Response<ContentdetailsAll> response) {

                    Log.e("contentDemoVideores", "is : " + new Gson().toJson(response.body().getData()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {


                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body().getData().get(0)).toString(), "contentDemoVideo");
                                GlobalBus.getBus().post(activityActivityMessage);


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, "Exception  "+ex.getMessage(),0);

                            Log.e("Exception","Exception :  "+ ex.getMessage());


                        }

                    }
                }

                @Override
                public void onFailure(Call<ContentdetailsAll> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void categoryCouress(final Context context, final RecyclerView recyclerchannel_video , String channel_id, final Loader loader  ) {

        Log.e("categoryCouress",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.categoryCouress(channel_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("categoryCouressres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();

                                Gson gson = new Gson();
                                transactions = gson.fromJson(Apiresponse, ChannelVideoResponse.class);
                                transactionsObjects = transactions.getData();

                                if (transactionsObjects.size() > 0) {
                                    mAdapter_catbyid = new CouressListByIdAdapter(transactionsObjects, context,Apiresponse );

                                    //  mLayoutManager = new GridLayoutManager(context,1);
                                   // recyclerchannel_video.setLayoutManager(mLayoutManager);

                                    recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(mAdapter_catbyid);
                                    recyclerchannel_video.setNestedScrollingEnabled(false);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);

                                } else {

                                    recyclerchannel_video.setVisibility(View.GONE);

                                }


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void All_categoryCouress(final Context context, final RecyclerView recyclerchannel_video , final String channel_id, final Loader loader  ) {

        Log.e("AllcategoryCouress",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.All_categoryCouress(channel_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("categoryCouressres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();

                                UtilMethods.INSTANCE.GetCourseDetail(context,channel_id+""  );


                                Gson gson = new Gson();
                                transactions = gson.fromJson(Apiresponse, ChannelVideoResponse.class);
                                transactionsObjects = transactions.getData();

                                if (transactionsObjects.size() > 0) {

                                    mAdapter_catbyid = new CouressListByIdAdapter(transactionsObjects, context,Apiresponse );
                                  //  mLayoutManager = new LinearLayoutManager(context);
                                 //   recyclerchannel_video.setLayoutManager(mLayoutManager);

                                    recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(mAdapter_catbyid);
                                    recyclerchannel_video.setNestedScrollingEnabled(false);
                                    recyclerchannel_video.setVisibility(View.VISIBLE);

                                } else {

                                    recyclerchannel_video.setVisibility(View.GONE);

                                }


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void View_All_categoryCouress(final Context context, final RecyclerView recyclerchannel_video , final String channel_id,
                                         final Loader loader  ) {

        Log.e("AllcategoryCouress",    " , user_id : "  + channel_id   );

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChannelVideoResponse> call = git.All_categoryCouress(channel_id);
            call.enqueue(new Callback<ChannelVideoResponse>() {

                @Override
                public void onResponse(Call<ChannelVideoResponse> call, final retrofit2.Response<ChannelVideoResponse> response) {

                    Log.e("categoryCouressres", "is : " + new Gson().toJson(response.body()).toString());


                    if (response != null) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        try {

                            if (response.body().getStatus().equalsIgnoreCase("1")  ) {/*

                                String Apiresponse="" + new Gson().toJson(response.body()).toString();

                               // UtilMethods.INSTANCE.GetCourseDetail(context,channel_id+""  );

                                Gson gson = new Gson();
                                transactions = gson.fromJson(Apiresponse, ChannelVideoResponse.class);
                                transactionsObjects = transactions.getData();

                                if (transactionsObjects.size() > 0) {
                                    ViewmAdapter = new ViewDetailCourseAdapter(transactionsObjects, context );

                                    mLayoutManager = new LinearLayoutManager(context);
                                    recyclerchannel_video.setLayoutManager(mLayoutManager);

                                    // recyclerchannel_video.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

                                    recyclerchannel_video.setItemAnimator(new DefaultItemAnimator());
                                    recyclerchannel_video.setAdapter(ViewmAdapter);
                                     recyclerchannel_video.setVisibility(View.VISIBLE);

                                } else {

                                    recyclerchannel_video.setVisibility(View.GONE);

                                }*/


                            } else {

                               // UtilMethods.INSTANCE.Error(context,"No  Resume upload",0);

                            }

                        } catch (Exception ex) {

                            UtilMethods.INSTANCE.Error(context, ""+ex.getMessage(),0);


                        }

                    }
                }

                @Override
                public void onFailure(Call<ChannelVideoResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    Toast.makeText(context, "Some technical issues!"+t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<MetasRespose> transactionscontentApi = new ArrayList<>();
    Setcontents contenttransactionsApi = new Setcontents();

    public void ImageUpload(Context mContext,SetChannelVideo operator, ArrayList<MetasRespose> metas, ImageView imagecouress) {

        String resposeApi=new Gson().toJson(operator).toString()+"";


        Gson gson = new Gson();
        contenttransactionsApi = gson.fromJson(resposeApi, Setcontents.class);
        transactionscontentApi = contenttransactionsApi.getMetas();

        if (transactionscontentApi.size() > 0) {

            for(int i=0;i<transactionscontentApi.size();i++) {

                String  wishid = transactionscontentApi.get(i).getOption();

                if(wishid.equalsIgnoreCase("cover")){

                    Log.e("getuserimage","image  :   "+ ApplicationConstant.INSTANCE.baseUrl +transactionscontentApi.get(i).getValue());

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.rnd_logo);
                    requestOptions.error(R.drawable.rnd_logo);

                    Glide.with(mContext)
                            .load( ApplicationConstant.INSTANCE.baseUrl +transactionscontentApi.get(i).getValue())
                            .apply(requestOptions)
                            .into(imagecouress);


                }else {


                }
            }


        } else {

        }


    }

}