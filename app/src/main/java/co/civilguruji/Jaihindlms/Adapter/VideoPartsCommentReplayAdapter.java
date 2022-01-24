package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.MsgChannelsub;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class VideoPartsCommentReplayAdapter extends RecyclerView.Adapter<VideoPartsCommentReplayAdapter.MyViewHolder> {

    private ArrayList<MsgChannelsub> transactionsList;
    private Context mContext;
    String response="";



    public class MyViewHolder extends RecyclerView.ViewHolder {
           TextView title,comment_val,comment_time;

        public MyViewHolder(View view) {
            super(view);

            comment_val =  view.findViewById(R.id.comment_val);
            title =  view.findViewById(R.id.title);
            comment_time =  view.findViewById(R.id.comment_time);

        }
    }

    public VideoPartsCommentReplayAdapter(ArrayList<MsgChannelsub> transactionsList, Context mContext , String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.response = response;


    }

    @Override
    public VideoPartsCommentReplayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactdetail_list_by_adapter_parts_reply, parent, false);

        return new VideoPartsCommentReplayAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final VideoPartsCommentReplayAdapter.MyViewHolder holder, int position) {
        final MsgChannelsub operator = transactionsList.get(position);

        holder.title.setText("" + operator.getName());
        holder.comment_val.setText("" + operator.getComment());
        final String[] split = operator.getCreatedAt().split(" ");
        holder.comment_time.setText("" + split[0]);


    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}