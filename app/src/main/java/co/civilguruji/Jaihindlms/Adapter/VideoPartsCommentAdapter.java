package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.MsgChannelsub;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class VideoPartsCommentAdapter extends RecyclerView.Adapter<VideoPartsCommentAdapter.MyViewHolder> {

    private ArrayList<MsgChannelsub> transactionsList;
    private Context mContext;
    String response="";



    public class MyViewHolder extends RecyclerView.ViewHolder {
           TextView title,description,comment_val,comment_time,comment_view;
           WebView webviewdescription;
           RecyclerView rv_comment_reply;

        public MyViewHolder(View view) {
            super(view);

            comment_val =  view.findViewById(R.id.comment_val);
            title =  view.findViewById(R.id.title);
            description =  view.findViewById(R.id.description);
            webviewdescription =  view.findViewById(R.id.webviewdescription);
            comment_time =  view.findViewById(R.id.comment_time);
            rv_comment_reply =  view.findViewById(R.id.rv_comment_reply);
            comment_view =  view.findViewById(R.id.comment_view);

        }
    }

    public VideoPartsCommentAdapter(ArrayList<MsgChannelsub> transactionsList, Context mContext , String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.response = response;

    }

    @Override
    public VideoPartsCommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactdetail_list_by_adapter_parts, parent, false);

        return new VideoPartsCommentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoPartsCommentAdapter.MyViewHolder holder, int position) {
        final MsgChannelsub operator = transactionsList.get(position);

        holder.title.setText("" + operator.getName());
        holder.comment_val.setText("" + operator.getComment());

        final String[] split = operator.getCreatedAt().split(" ");

        holder.comment_time.setText("" + split[0]);

        UtilMethods.INSTANCE.Get_Comment_reply(mContext,holder.rv_comment_reply,operator.getId(),holder.comment_view);


        holder.comment_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.rv_comment_reply.getVisibility()==View.VISIBLE){
                    holder.rv_comment_reply.setVisibility(View.GONE);
                }else if(holder.rv_comment_reply.getVisibility()==View.GONE){
                    holder.rv_comment_reply.setVisibility(View.VISIBLE);
                }

            }
        });

        //    holder.description.setText("" + operator.getDescription() );
        //  String htmlAsString = operator.getDescription().toString().trim();  // used by WebView
       //    holder.webviewdescription.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}