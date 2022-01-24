package co.civilguruji.Jaihindlms.Fragment.SubFragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;

public class VideoDescriptionFragment extends Fragment {

    SetChannelVideo operator;
    TextView tittle;
    WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_detail_courses, container, false);


        GetId(v);


        return v;


    }

    private void GetId(View v) {

        tittle=v.findViewById(R.id.tittle);
        webview=v.findViewById(R.id.webview);


        SharedPreferences myPreferences =  getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, getActivity().MODE_PRIVATE);
        String responseApi = myPreferences.getString(ApplicationConstant.INSTANCE.part_description, "");

        tittle.setText("  "+responseApi);
        String htmlAsString = responseApi.toString().trim();  // used by WebView
        webview.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);

    }
}