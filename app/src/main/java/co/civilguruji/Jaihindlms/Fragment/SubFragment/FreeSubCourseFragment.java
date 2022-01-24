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

import co.civilguruji.Jaihindlms.Adapter.GetPackageAdapter;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.PackageRespose;
import co.civilguruji.Jaihindlms.Utils.PackageSubDemo;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;
public class FreeSubCourseFragment extends Fragment {

    RecyclerView packege_rc;
    Loader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_free_sub_course, container, false);

        Getid(v);

        return v;


    }

    private void Getid(View v) {

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
         packege_rc=v.findViewById(R.id.packege_rc);



        hitApi();

     //  ShowBlinkText();

    }


    private void hitApi() {

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

         UtilMethods.INSTANCE.getpackage(getActivity(), loader);


     //  UtilMethods.INSTANCE.Course_cat(getActivity(), loader);


    }


    GetPackageAdapter mAdapter;
     ArrayList<PackageSubDemo> transactionsObjects = new ArrayList<>();
    PackageRespose transactions = new PackageRespose();

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("getpackage")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }

    public void dataParse(String response) {


        Log.e("responseresponse",""+ response);

        Gson gson = new Gson();
        transactions = gson.fromJson(response, PackageRespose.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {
            mAdapter = new GetPackageAdapter(transactionsObjects, getActivity());
            packege_rc.setLayoutManager(new GridLayoutManager(getActivity(), 2,LinearLayoutManager.VERTICAL, false));
            packege_rc.setItemAnimator(new DefaultItemAnimator());
            packege_rc.setAdapter(mAdapter);
            packege_rc.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();

            packege_rc.setVisibility(View.VISIBLE);

        } else {
            packege_rc.setVisibility(View.GONE);
        }



    }



}