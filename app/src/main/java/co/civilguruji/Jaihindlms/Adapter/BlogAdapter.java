package co.civilguruji.Jaihindlms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import Jaihindlms.R;
import co.civilguruji.Jaihindlms.Activity.BlogDetailActivity;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    onItemClickListner onItemClickListner;


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView title ,date;
         CircleImageView image_post;
         WebView webview;
         RelativeLayout click_blog;

        public MyViewHolder(View view) {
            super(view);

             title =  view.findViewById(R.id.title);
            date =  view.findViewById(R.id.date);
            webview =  view.findViewById(R.id.webview);
            image_post =  view.findViewById(R.id.image_post);
            click_blog =  view.findViewById(R.id.click_blog);

        }
    }

    public BlogAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public BlogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_blog_adapter, parent, false);

        return new BlogAdapter.MyViewHolder(itemView);

    }



    public void setOnItemClickListner(BlogAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final BlogAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);


        holder.title.setText("" + operator.getTitle());
        holder.date.setText("" + operator.getTags());


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load( ApplicationConstant.INSTANCE.ImageBAseUrl +operator.getImage())
                .apply(requestOptions)
                .into(holder.image_post);

        holder.click_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mContext, BlogDetailActivity.class);
                i.putExtra("position",""+new Gson().toJson(operator));
                mContext.startActivity(i);


            }
        });



     /*   String htmlAsString = operator.getContent().toString().trim();  // used by WebView
        holder.webview.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);
*/


    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}