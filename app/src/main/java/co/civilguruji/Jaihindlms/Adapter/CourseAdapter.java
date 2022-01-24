package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Activity.ViewAllCategoryActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.UtilMethods;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    onItemClickListner onItemClickListner;
    String responseApi;


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView couress_category ;
          RelativeLayout click_job;
          RecyclerView recycler_couress;
          LinearLayout View_all_li;




        public MyViewHolder(View view) {
            super(view);

            couress_category =  view.findViewById(R.id.couress_category);
             click_job =  view.findViewById(R.id.click_job);
            recycler_couress =  view.findViewById(R.id.recycler_couress);
            View_all_li =  view.findViewById(R.id.View_all_li);

        }
    }

    public CourseAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ,String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.responseApi = response;


    }

    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_category_new_adapter, parent, false);

        return new CourseAdapter.MyViewHolder(itemView);

    }



    public void setOnItemClickListner(CourseAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final CourseAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        holder.couress_category.setText("" + operator.getTitle());
        UtilMethods.INSTANCE.All_categoryCouress(mContext,holder.recycler_couress,operator.getId()+"",null);


        holder.View_all_li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mContext, ViewAllCategoryActivity.class);
                i.putExtra("responseApi",""+responseApi);
                mContext.startActivity(i);



            }
        });





    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}