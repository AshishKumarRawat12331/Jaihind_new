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
import co.civilguruji.Jaihindlms.Utils.Channelsub;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private ArrayList<Channelsub> transactionsList;
    private Context mContext;
    onItemClickListner onItemClickListner;


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView title ,tv_date;
         WebView tv_description;


        public MyViewHolder(View view) {
            super(view);

             title =  view.findViewById(R.id.tv_title);
            tv_date =  view.findViewById(R.id.tv_date);
            tv_description =  view.findViewById(R.id.tv_description);


        }
    }

    public NotificationAdapter(ArrayList<Channelsub> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_notification_adapter, parent, false);

        return new NotificationAdapter.MyViewHolder(itemView);

    }



    public void setOnItemClickListner(NotificationAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final NotificationAdapter.MyViewHolder holder, int position) {
        final Channelsub operator = transactionsList.get(position);

        holder.title.setText("" + operator.getTitle());

        holder.tv_description.loadDataWithBaseURL(null, operator.getMsg(), "text/html", "utf-8", null);

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}