package co.civilguruji.Jaihindlms.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Adapter.NotificationAdapter;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.Channelsub;
import co.civilguruji.Jaihindlms.Utils.ContentdetailsAll;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class NotificationFragment extends BottomSheetDialogFragment implements View.OnClickListener {

RelativeLayout rlCancel;
TextView tv_header_name;
RecyclerView rv_notification;
Loader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        getIds(v);

        return v;


    }

    private void getIds(View v) {

        rlCancel = (RelativeLayout)v.findViewById(R.id.rl_close);
        tv_header_name = v.findViewById(R.id.tv_header_name);
        tv_header_name.setText("Notification");

        setListners();

        rv_notification=v.findViewById(R.id.rv_notification);


        hitApi();

    }

    private void hitApi() {

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.Get_Notification(getActivity(), loader);

    }
    NotificationAdapter mAdapter;
    ArrayList<Channelsub> transactionsObjects = new ArrayList<>();
    ContentdetailsAll transactions = new ContentdetailsAll();
    LinearLayoutManager mLayoutManager;

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("get_notification")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }

    public void dataParse(String response) {

        Gson gson = new Gson();
        transactions = gson.fromJson(response, ContentdetailsAll.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {
            mAdapter = new NotificationAdapter(transactionsObjects, getActivity());
            rv_notification.setLayoutManager(new GridLayoutManager(getActivity(), 1,LinearLayoutManager.VERTICAL, false));
            rv_notification.setItemAnimator(new DefaultItemAnimator());
            rv_notification.setAdapter(mAdapter);
            rv_notification.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();
            rv_notification.setVisibility(View.VISIBLE);

        } else {
            rv_notification.setVisibility(View.GONE);

            UtilMethods.INSTANCE.Error(getActivity(),"Empty Value",0);


        }

    }



    private void setListners() {
        rlCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==rlCancel)
        {
            dismiss();
        }
    }


}
