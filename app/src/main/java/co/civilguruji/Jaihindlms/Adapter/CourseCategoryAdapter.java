package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class CourseCategoryAdapter extends RecyclerView.Adapter<CourseCategoryAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    onItemClickListner onItemClickListner;


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView channel_title ;
          RelativeLayout click_job;




        public MyViewHolder(View view) {
            super(view);

            channel_title =  view.findViewById(R.id.channel_title);
             click_job =  view.findViewById(R.id.click_job);

        }
    }

    public CourseCategoryAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public CourseCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_category_adapter, parent, false);

        return new CourseCategoryAdapter.MyViewHolder(itemView);

    }



    public void setOnItemClickListner(CourseCategoryAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final CourseCategoryAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        holder.channel_title.setText("" + operator.getTitle());





        holder.click_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClickListner.onClick(""+operator.getId());

            }
        });





    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}