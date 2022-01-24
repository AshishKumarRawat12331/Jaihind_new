package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class ContectDetailListByIdAdapter extends RecyclerView.Adapter<ContectDetailListByIdAdapter.MyViewHolder> {

    private ArrayList<PartsChannelVideo> transactionsList;
    private Context mContext;
    String response="";



    public class MyViewHolder extends RecyclerView.ViewHolder {
           TextView title,description;
           WebView webviewdescription;




        public MyViewHolder(View view) {
            super(view);

            title =  view.findViewById(R.id.title);
            description =  view.findViewById(R.id.description);
            webviewdescription =  view.findViewById(R.id.webviewdescription);

        }
    }

    public ContectDetailListByIdAdapter(ArrayList<PartsChannelVideo> transactionsList, Context mContext , String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.response = response;


    }

    @Override
    public ContectDetailListByIdAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactdetail_list_by_adapter, parent, false);

        return new ContectDetailListByIdAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ContectDetailListByIdAdapter.MyViewHolder holder, int position) {
        final PartsChannelVideo operator = transactionsList.get(position);


         holder.title.setText("" + operator.getTitle());
         holder.description.setText("" + operator.getDescription() );

        String htmlAsString = operator.getDescription().toString().trim();  // used by WebView

    //    holder.webviewdescription.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);



    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}