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

import co.civilguruji.Jaihindlms.Activity.VideoCommentDescriptionActivity;
import co.civilguruji.Jaihindlms.ApiRespose.PartsChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class PartVideoAdapter extends RecyclerView.Adapter<PartVideoAdapter.MyViewHolder> {

    private ArrayList<PartsChannelVideo> transactionsList;
    private Context mContext;
    String youtubelink;
    onItemClickListner onItemClickListner;


    public void filter(ArrayList<PartsChannelVideo> newlist) {

        transactionsList=new ArrayList<>();
        transactionsList.addAll(newlist);
        notifyDataSetChanged();


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView title ;
         ImageView image_post;
         RelativeLayout click_blog;


        public MyViewHolder(View view) {
            super(view);

             title =  view.findViewById(R.id.title);

            image_post =  view.findViewById(R.id.image_post);
            click_blog =  view.findViewById(R.id.click_blog);

        }
    }

    public PartVideoAdapter(ArrayList<PartsChannelVideo> transactionsList, Context mContext ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;


    }

    @Override
    public PartVideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.couress_part_list_adapter, parent, false);

        return new PartVideoAdapter.MyViewHolder(itemView);

    }

    public void setOnItemClickListner(PartVideoAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(String str);//pass your object types.
    }

    @Override
    public void onBindViewHolder(final PartVideoAdapter.MyViewHolder holder, int position) {
        final PartsChannelVideo operator = transactionsList.get(position);

        holder.title.setText("" + operator.getTitle());

        final String videolinkre=""+ operator.getUpload_video().replace("https://youtu.be/","");
        String img_url="http://img.youtube.com/vi/"+videolinkre.replace("?t=6","")+"/0.jpg"; // this is link which will give u thumnail image of that video

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load( img_url)
                .apply(requestOptions)
                .into(holder.image_post);



        holder.click_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



             /*   youtubelink=""+ operator.getUpload_video().toString().trim();
                Intent i = new Intent(mContext, YoutubeVideoPlayNew.class);
                i.putExtra("video_id",""+youtubelink);
                mContext.startActivity(i);*/

                Intent i=new Intent(mContext, VideoCommentDescriptionActivity.class);
                i.putExtra("response", ""+new Gson().toJson(operator).toString());
                mContext.startActivity(i);



                /* Intent i=new Intent(mContext, PlaySelectVideoNew.class);
                 i.putExtra("videoid",""+videolinkre.replace("?t=6",""));
                i.putExtra("discriptiom", ""+operator.getTitle() );
                i.putExtra("response", ""+"response");
                mContext.startActivity(i);*/


            }

        });

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}