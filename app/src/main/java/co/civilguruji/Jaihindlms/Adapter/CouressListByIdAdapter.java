package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Activity.CouressDetailPAyActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class CouressListByIdAdapter extends RecyclerView.Adapter<CouressListByIdAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    String response="";



    public class MyViewHolder extends RecyclerView.ViewHolder {
           ImageView image_couress;
           TextView title,amount,time;
           RelativeLayout headerContent;




        public MyViewHolder(View view) {
            super(view);

            image_couress =  view.findViewById(R.id.imagecouress);
            title =  view.findViewById(R.id.title);
            amount =  view.findViewById(R.id.amount);
            time =  view.findViewById(R.id.time);
            headerContent =  view.findViewById(R.id.headerContent);

        }
    }

    public CouressListByIdAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext , String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.response = response;


    }

    @Override
    public CouressListByIdAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_list_by_adapter, parent, false);

        return new CouressListByIdAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final CouressListByIdAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);



        Log.e("requestOptions",""+ ""+operator.getPrice());

        Glide.with(mContext)
                .load( ""+operator.getThumbnail())
                .apply(requestOptions)
                .into(holder.image_couress);

         holder.title.setText("" + operator.getTitle());
         holder.amount.setText("" + operator.getCurrency() +" "+ operator.getFinal_price());
         holder.time.setText("" + operator.getDuration());

         holder.headerContent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent i=new Intent(mContext, CouressDetailPAyActivity.class);
                 i.putExtra("response", ""+new Gson().toJson(operator).toString());
                 i.putExtra("prisedetail", ""+new Gson().toJson(operator.getPrice()).toString());
                 i.putExtra("packagename", ""+operator.getTitle());
                 i.putExtra("CouressId", ""+"");
                 mContext.startActivity(i);

             }
         });

}

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}