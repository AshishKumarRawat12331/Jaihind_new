package co.civilguruji.Jaihindlms.Fragment.SubFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.CourseEnrollAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class FreeEnrolledCourseFragment extends Fragment {

    RecyclerView recycler_course_cat;
    Loader loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_free_enrolled, container, false);


        Getid(v);

        return v;

    }

    private void Getid(View v) {

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        recycler_course_cat=v.findViewById(R.id.recycler_course_cat);


        hitApi();

    }


    private void hitApi() {

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.packageuserbuy(getActivity(), loader);



    }

    CourseEnrollAdapter mAdapter;
    ArrayList<SetChannelVideo> transactionsObjects = new ArrayList<>();
    ChannelVideoResponse transactions = new ChannelVideoResponse();

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("contentuserbuy")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }


    public void dataParse(String response) {


        Log.e("responseresponse",""+ response);

        Gson gson = new Gson();
        transactions = gson.fromJson(response, ChannelVideoResponse.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {

            mAdapter = new CourseEnrollAdapter(transactionsObjects, getActivity());

            recycler_course_cat.setLayoutManager(new GridLayoutManager(getActivity(), 1,LinearLayoutManager.VERTICAL, false));
            recycler_course_cat.setItemAnimator(new DefaultItemAnimator());
            recycler_course_cat.setAdapter(mAdapter);
            recycler_course_cat.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();


            recycler_course_cat.setVisibility(View.VISIBLE);

        } else {
            recycler_course_cat.setVisibility(View.GONE);
        }



    }

}