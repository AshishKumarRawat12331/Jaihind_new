package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Activity.EnrollVideoCategoryActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class CourseSearchAdapter extends RecyclerView.Adapter<CourseSearchAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;


    public void filter(ArrayList<SetChannelVideo>newList)
    {
        transactionsList=new ArrayList<>();
        transactionsList.addAll(newList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title ,time,amount;
        RelativeLayout headerContent;

        ImageView imagecouress;

        public MyViewHolder(View view) {
            super(view);

            amount =  view.findViewById(R.id.amount);
            title =  view.findViewById(R.id.title);
            time =  view.findViewById(R.id.time);
            headerContent =  view.findViewById(R.id.headerContent);
            imagecouress =  view.findViewById(R.id.imagecouress);

        }
    }

    public CourseSearchAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public CourseSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_enroll_adapter, parent, false);

        return new CourseSearchAdapter.MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final CourseSearchAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

         holder.title.setText("" + operator.getTitle());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load( ""+ operator.getThumbnail())
                .apply(requestOptions)
                .into(holder.imagecouress);


        holder.headerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, EnrollVideoCategoryActivity.class);
                i.putExtra("enroll_id",""+operator.getId());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}