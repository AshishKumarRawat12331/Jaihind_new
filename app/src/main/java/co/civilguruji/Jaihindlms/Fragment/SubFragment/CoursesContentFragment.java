package co.civilguruji.Jaihindlms.Fragment.SubFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class CoursesContentFragment extends Fragment {
    RecyclerView recycler_course_cat;
    Loader loader;
    RelativeLayout msg_box;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_traning_courses, container, false);



        getid(v);

        return v;

    }

    private void getid(View v) {

        msg_box=v.findViewById(R.id.msg_box);
        recycler_course_cat=v.findViewById(R.id.recycler_course_cat);

        msg_box.setVisibility(View.GONE);

        SharedPreferences myPreferences =  getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, getActivity().MODE_PRIVATE);
        String Course_idResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Course_id, "");

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.contentdetails(getActivity(),recycler_course_cat,Course_idResponse,loader);


    }
}