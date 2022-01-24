package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Activity.BlogCategoryActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class BlogCategoryAdapter extends RecyclerView.Adapter<BlogCategoryAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    onItemClickListner onItemClickListner;


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView title ;
         RelativeLayout click_catgory;


        public MyViewHolder(View view) {
            super(view);

             title =  view.findViewById(R.id.title);
            click_catgory =  view.findViewById(R.id.click_catgory);


        }
    }

    public BlogCategoryAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;

    }

    @Override
    public BlogCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_catgory_adapter, parent, false);

        return new BlogCategoryAdapter.MyViewHolder(itemView);

    }



    public void setOnItemClickListner(BlogCategoryAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final BlogCategoryAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        holder.title.setText("" + operator.getTitle() +" [ "+operator.getPosts_count()+" ]");


        holder.click_catgory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mContext, BlogCategoryActivity.class);
                i.putExtra("blog_id",""+operator.getId());
                i.putExtra("name",""+operator.getTitle());
                mContext.startActivity(i);



            }
        });

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}