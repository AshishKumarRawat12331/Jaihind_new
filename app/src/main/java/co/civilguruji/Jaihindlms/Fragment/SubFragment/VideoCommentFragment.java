package co.civilguruji.Jaihindlms.Fragment.SubFragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class VideoCommentFragment extends Fragment implements View.OnClickListener {
    RecyclerView recycler_course_cat;
    Loader loader;
    EditText ed_Comment;
    TextView tv_Submit;
    String Course_idResponse="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_traning_courses, container, false);

        getid(v);

        return v;

    }

    private void getid(View v) {

        tv_Submit=v.findViewById(R.id.tv_Submit);
        ed_Comment=v.findViewById(R.id.ed_Comment);
        tv_Submit.setOnClickListener(this);
        
        recycler_course_cat=v.findViewById(R.id.recycler_course_cat);

        SharedPreferences myPreferences =  getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, getActivity().MODE_PRIVATE);
        Course_idResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Part_video_id, "");

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.GetproductComment(getActivity(),recycler_course_cat,Course_idResponse,loader);


    }

    @Override
    public void onClick(View view) {
        
        if(view==tv_Submit){

            if(!ed_Comment.getText().toString().trim().isEmpty()){

                if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                    loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);

                    loader.show();
                    loader.setCancelable(false);
                    loader.setCanceledOnTouchOutside(false);

                    UtilMethods.INSTANCE.ProductComment(getActivity(),ed_Comment.getText().toString().trim()+"",Course_idResponse,loader );


                } else {
                    UtilMethods.INSTANCE.Error(getActivity(),
                            getResources().getString(R.string.network_error_message),0);
                }

            }else {

                Toast.makeText(getActivity(), "Please Enter Your Comment", Toast.LENGTH_SHORT).show();
                
                
            }
            
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("productComment")) {

            ed_Comment.setText("");

            UtilMethods.INSTANCE.GetproductComment(getActivity(),recycler_course_cat,Course_idResponse,loader);

        }

    }

}