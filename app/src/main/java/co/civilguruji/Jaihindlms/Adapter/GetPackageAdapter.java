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

import co.civilguruji.Jaihindlms.Activity.CourseDetailCategoryActivity;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.PackageSubDemo;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class GetPackageAdapter extends RecyclerView.Adapter<GetPackageAdapter.MyViewHolder> {

    private ArrayList<PackageSubDemo> transactionsList;
    private Context mContext;

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

    public GetPackageAdapter(ArrayList<PackageSubDemo> transactionsList, Context mContext  ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;

    }

    @Override
    public GetPackageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_view_detail_adapter, parent, false);

        return new GetPackageAdapter.MyViewHolder(itemView);

    }



    @Override
    public void onBindViewHolder(final GetPackageAdapter.MyViewHolder holder, int position) {
        final PackageSubDemo operator = transactionsList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load( "https://civilguruji.in/public/assets/package/"+operator.getCover())
                .apply(requestOptions)
                .into(holder.imagecouress);


        holder.title.setText("" + operator.getTitle());
        holder.amount.setText("  Rs." +"  "+ operator.getPrice());
        holder.time.setText("" + operator.getDay() +" Day ");

        // holder.time.setText("Validity of " + operator.getDay() +" Day ");

        holder.headerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Toast.makeText(mContext, ""+operator.getCourseId(), Toast.LENGTH_SHORT).show();

                Intent i=new Intent(mContext, CourseDetailCategoryActivity.class);
                i.putExtra("CourseId", ""+operator.getCourseId());
                i.putExtra("amount", ""+operator.getPrice());
                i.putExtra("packageid", ""+operator.getId());
                i.putExtra("packagename", ""+operator.getTitle());
                mContext.startActivity(i);



            }
        });


    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}