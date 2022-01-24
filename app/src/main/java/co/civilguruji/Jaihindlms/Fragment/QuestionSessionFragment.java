package co.civilguruji.Jaihindlms.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import co.civilguruji.Jaihindlms.R;

public class QuestionSessionFragment extends Fragment  {

    LinearLayout course,enrolled;
    View v1,v2;
    TextView Course_tv,Enrolled_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_question_session, container, false);

       // Getid(v);

        return v;


    }


}