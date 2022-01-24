package co.civilguruji.Jaihindlms.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.ChannelListAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.DataLogin;
import co.civilguruji.Jaihindlms.ApiRespose.Showchannels;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class AskaQuestionFragment extends Fragment implements View.OnClickListener {

    RecyclerView recycler_channel;
    Loader loader;
    RelativeLayout search_course;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_askaquestion, container, false);

        Getid(v);

        return v;

    }

    private void Getid(View v) {


        recycler_channel=v.findViewById(R.id.recycler_channel);
        search_course=v.findViewById(R.id.search_course);
        search_course.setOnClickListener(this);

        hitApi();
    }

    private void hitApi() {

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.Getchannel(getActivity(), loader);

    }

    ChannelListAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<Showchannels> transactionsObjects = new ArrayList<>();
    DataLogin transactions = new DataLogin();

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    public void dataParse(String response) {

        Gson gson = new Gson();
        transactions = gson.fromJson(response, DataLogin.class);
        transactionsObjects = transactions.getChannels();

        if (transactionsObjects.size() > 0) {
            mAdapter = new ChannelListAdapter(transactionsObjects, getActivity());
            mLayoutManager = new GridLayoutManager(getActivity(),1);
            recycler_channel.setLayoutManager(mLayoutManager);
            recycler_channel.setItemAnimator(new DefaultItemAnimator());
            recycler_channel.setAdapter(mAdapter);
            recycler_channel.setNestedScrollingEnabled(false);
            recycler_channel.setVisibility(View.VISIBLE);
         } else {
            recycler_channel.setVisibility(View.GONE);
         }

    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("getchannel")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }


    @Override
    public void onClick(View view) {

        if(view==search_course){


           // startActivity(new Intent(getActivity(), SearchActivity.class));

            new OperatorFragment().show(getFragmentManager(), "mobileType");

        }



    }
}