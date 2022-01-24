package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.ApiRespose.Showchannels;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.MyViewHolder> {

    private ArrayList<Showchannels> transactionsList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView channel_title ;
         RecyclerView recycler_channel_video;




        public MyViewHolder(View view) {
            super(view);

            channel_title =  view.findViewById(R.id.channel_title);
            recycler_channel_video =  view.findViewById(R.id.recycler_channel_video);

        }
    }

    public ChannelListAdapter(ArrayList<Showchannels> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public ChannelListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_list_adapter, parent, false);

        return new ChannelListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ChannelListAdapter.MyViewHolder holder, int position) {
        final Showchannels operator = transactionsList.get(position);

        holder.channel_title.setText("" + operator.getTitle());

        UtilMethods.INSTANCE.ChannelVideo(mContext,holder.recycler_channel_video,""+operator.getId(),null);


    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}