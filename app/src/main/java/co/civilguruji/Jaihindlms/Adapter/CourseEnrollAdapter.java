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

import co.civilguruji.Jaihindlms.Activity.GetPackageCourseCategoryActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

/*
 * Created by Uzair khan on 18-04-2020.
 */

public class CourseEnrollAdapter extends RecyclerView.Adapter<CourseEnrollAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title ,time,amount,time_days,count_couress;
        RelativeLayout headerContent;

        ImageView imagecouress;

        public MyViewHolder(View view) {
            super(view);

            amount =  view.findViewById(R.id.amount);
            title =  view.findViewById(R.id.title);
            time =  view.findViewById(R.id.time);
            headerContent =  view.findViewById(R.id.headerContent);
            imagecouress =  view.findViewById(R.id.imagecouress);
            time_days =  view.findViewById(R.id.time_days);
            count_couress =  view.findViewById(R.id.count_couress);

        }
    }

    public  CourseEnrollAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public CourseEnrollAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_enroll_adapter, parent, false);

        return new CourseEnrollAdapter.MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final CourseEnrollAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);


        holder.title.setText("" + operator.getTitle());
        holder.time_days.setText("Validity of " + operator.getDay() +" Day ");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load( "https://civilguruji.in/public/assets/package/"+operator.getCover())
                .apply(requestOptions)
                .into(holder.imagecouress );


       // UtilMethods.INSTANCE.ImageUpload(mContext,operator,operator.getMetas(),holder.imagecouress );

        UtilMethods.INSTANCE.PackagecourseCount(mContext,operator.getCourse_id(),null,holder.count_couress);





        holder.headerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mContext, GetPackageCourseCategoryActivity.class);
                i.putExtra("CourseId", ""+operator.getCourse_id());
                i.putExtra("amount", ""+operator.getPrice());
                i.putExtra("packageid", ""+operator.getId());
                i.putExtra("getinroll", "1");
                i.putExtra("packagename", ""+operator.getTitle());
                 mContext.startActivity(i);




               /* Intent i=new Intent(mContext, EnrollVideoCategoryActivity.class);
                i.putExtra("enroll_id",""+operator.getId());
                mContext.startActivity(i);*/


            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}