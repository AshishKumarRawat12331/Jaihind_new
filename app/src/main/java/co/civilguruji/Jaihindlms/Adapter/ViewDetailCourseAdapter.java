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
import com.google.gson.Gson;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Activity.CouressDetailPAyActivity;
import co.civilguruji.Jaihindlms.Activity.EnrollVideoCategoryActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class ViewDetailCourseAdapter extends RecyclerView.Adapter<ViewDetailCourseAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    String amount_pk;
    String CouressId;
    String packagename;
    String getinroll;
    onItemClickListner onItemClickListner;

    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView title ,time,amount;
          RelativeLayout headerContent,Ri_amount;
          ImageView imagecouress;

        public MyViewHolder(View view) {
            super(view);

            amount =  view.findViewById(R.id.amount);
            title =  view.findViewById(R.id.title);
            time =  view.findViewById(R.id.time);
            headerContent =  view.findViewById(R.id.headerContent);
            Ri_amount =  view.findViewById(R.id.Ri_amount);
            imagecouress =  view.findViewById(R.id.imagecouress);

        }
    }

    public ViewDetailCourseAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ,
                                   String amount_pk,String getinroll,String packagename ,String CouressId) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.amount_pk = amount_pk;
        this.CouressId = CouressId;
        this.packagename = packagename;
        this.getinroll = getinroll;

    }

    @Override
    public ViewDetailCourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_view_detail_adapter, parent, false);

        return new ViewDetailCourseAdapter.MyViewHolder(itemView);

    }

    public void setOnItemClickListner(ViewDetailCourseAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final ViewDetailCourseAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);


        Glide.with(mContext)
                .load( ""+operator.getThumbnail())
                .apply(requestOptions)
                .into(holder.imagecouress);

        holder.title.setText("" + operator.getTitle());
     //   holder.amount.setText("" + operator.getCurrency() +" "+ operator.getPrice());
        holder.time.setText("" + operator.getDuration());

        holder.Ri_amount.setVisibility(View.GONE);

        holder.headerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(getinroll.equalsIgnoreCase("1")){

                    Intent i=new Intent(mContext, EnrollVideoCategoryActivity.class);
                    i.putExtra("enroll_id",""+operator.getId());
                    i.putExtra("type","get");
                    i.putExtra("amount",""+amount_pk);
                    mContext.startActivity(i);

                }else {

                    Intent i=new Intent(mContext, CouressDetailPAyActivity.class);
                    i.putExtra("amountpk", ""+amount_pk);
                    i.putExtra("CouressId", ""+CouressId);
                    i.putExtra("response", ""+new Gson().toJson(operator).toString());
                    i.putExtra("prisedetail", ""+new Gson().toJson(operator.getPrice()).toString());
                    i.putExtra("packagename", ""+packagename);
                    mContext.startActivity(i);


                }






              /* */



            }
        });

    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}