package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class ChannelSubCatListAdapter extends RecyclerView.Adapter<ChannelSubCatListAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    String response="";

    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView couress_title ,viewAll;
         RecyclerView recycler_couress_sub;

        public MyViewHolder(View view) {
            super(view);

            couress_title =  view.findViewById(R.id.couress_title);
            viewAll =  view.findViewById(R.id.viewAll);
            recycler_couress_sub =  view.findViewById(R.id.recycler_couress_sub);

        }
    }

    public ChannelSubCatListAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext , String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.response = response;

    }

    @Override
    public ChannelSubCatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_sub_cat_adapter, parent, false);

        return new ChannelSubCatListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ChannelSubCatListAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        holder.couress_title.setText("" + operator.getTitle());

        UtilMethods.INSTANCE.categoryCouress(mContext,holder.recycler_couress_sub,operator.getId()+"",null);


    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}