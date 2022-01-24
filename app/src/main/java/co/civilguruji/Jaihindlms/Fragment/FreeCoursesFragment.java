package co.civilguruji.Jaihindlms.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import co.civilguruji.Jaihindlms.Fragment.SubFragment.FreeEnrolledCourseFragment;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.FreeSubCourseFragment;
import co.civilguruji.Jaihindlms.R;

public class FreeCoursesFragment extends Fragment implements View.OnClickListener {

    LinearLayout course,enrolled;
    View v1,v2;
    TextView Course_tv,Enrolled_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_free, container, false);

        Getid(v);

        return v;


    }

    private void Getid(View v) {

        Course_tv=v.findViewById(R.id.Course_tv);
        Enrolled_tv=v.findViewById(R.id.Enrolled_tv);


        enrolled=v.findViewById(R.id.enrolled);
        course=v.findViewById(R.id.course);


        v1=v.findViewById(R.id.v1);
        v2=v.findViewById(R.id.v2);

        enrolled.setOnClickListener(this);
        course.setOnClickListener(this);

        v1.setOnClickListener(this);
        v2.setOnClickListener(this);



        Course_tv.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        v1.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

        Enrolled_tv.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
        v2.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.INVISIBLE);

        changeFragment(new FreeSubCourseFragment());

    }

    private void changeFragment(Fragment targetFragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onClick(View view) {


        if(view==course){

            Course_tv.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            v1.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

             Enrolled_tv.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
            v2.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.INVISIBLE);

            changeFragment(new FreeSubCourseFragment());

        }

        if(view==enrolled){

            Course_tv.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
            v1.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

            Enrolled_tv.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            v2.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));


            v2.setVisibility(View.VISIBLE);
            v1.setVisibility(View.INVISIBLE);

            changeFragment(new FreeEnrolledCourseFragment());


        }





    }
}