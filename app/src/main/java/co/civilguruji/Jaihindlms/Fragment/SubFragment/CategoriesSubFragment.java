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

import co.civilguruji.Jaihindlms.Adapter.BlogCategoryAdapter;
import co.civilguruji.Jaihindlms.ApiRespose.ChannelVideoResponse;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
import co.civilguruji.Jaihindlms.Utils.GlobalBus;
import co.civilguruji.Jaihindlms.Utils.Loader;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

public class CategoriesSubFragment extends Fragment {

    RecyclerView recycler_category;
    Loader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_category_sub, container, false);

        Getid(v);

        return v;

    }

    private void Getid(View v) {

        recycler_category=v.findViewById(R.id.recycler_category);



        hitApi();
    }

    @Override
    public void onResume() {


        super.onResume();

        hitApi();


    }

    private void hitApi() {

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.blogcategory(getActivity(), loader);


    }


    BlogCategoryAdapter mAdapter;
    ArrayList<SetChannelVideo> transactionsObjects = new ArrayList<>();
    ChannelVideoResponse transactions = new ChannelVideoResponse();
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

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("blogcategory")) {

            dataParse(activityFragmentMessage.getMessage());

        }

    }

    public void dataParse(String response) {

        Log.e("responseresponse",""+ response);

        Gson gson = new Gson();
        transactions = gson.fromJson(response, ChannelVideoResponse.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {
            mAdapter = new BlogCategoryAdapter(transactionsObjects, getActivity());
            recycler_category.setLayoutManager(new GridLayoutManager(getActivity(), 1,LinearLayoutManager.VERTICAL, false));
            recycler_category.setItemAnimator(new DefaultItemAnimator());
            recycler_category.setAdapter(mAdapter);
            recycler_category.setNestedScrollingEnabled(false);
            mAdapter.notifyDataSetChanged();
            recycler_category.setVisibility(View.VISIBLE);
        } else {
            recycler_category.setVisibility(View.GONE);
        }



    }



}