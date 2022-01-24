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

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.Activity.PlayVideoNew;
import co.civilguruji.Jaihindlms.ApiRespose.SetChannelVideo;
import co.civilguruji.Jaihindlms.R;

/**
 * Created by Uzair khan on 18-04-2020.
 */

public class ChannelVideoListAdapter extends RecyclerView.Adapter<ChannelVideoListAdapter.MyViewHolder> {

    private ArrayList<SetChannelVideo> transactionsList;
    private Context mContext;
    String response="";



    public class MyViewHolder extends RecyclerView.ViewHolder {
         public TextView channel_title ;
         ImageView recent_channel_image;
         RelativeLayout headerContent;




        public MyViewHolder(View view) {
            super(view);

            recent_channel_image =  view.findViewById(R.id.recent_channel_image);
            channel_title =  view.findViewById(R.id.title);
            headerContent =  view.findViewById(R.id.headerContent);

        }
    }

    public ChannelVideoListAdapter(ArrayList<SetChannelVideo> transactionsList, Context mContext ,String response) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.response = response;


    }

    @Override
    public ChannelVideoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_vide_list_adapter, parent, false);

        return new ChannelVideoListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ChannelVideoListAdapter.MyViewHolder holder, int position) {
        final SetChannelVideo operator = transactionsList.get(position);

        holder.channel_title.setText("" + operator.getTitle());

        final String videolinkre=""+ operator.getViedoLink().replace("https://www.youtube.com/embed/","");


        String img_url="http://img.youtube.com/vi/"+videolinkre.replace("https://youtu.be/","")+"/0.jpg"; // this is link which will give u thumnail image of that video

        Log.e("img_urlll",""+ img_url);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load( img_url+"")
                .apply(requestOptions)
                .into(holder.recent_channel_image);





        holder.headerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(mContext, PlayVideoNew.class);
                //  i.putExtra("videoid",""+"9XC19zNPsNE");
                i.putExtra("videoid",""+videolinkre.replace("https://youtu.be/",""));
                i.putExtra("discriptiom", ""+operator.getTitle() );
                i.putExtra("response", ""+response);
                mContext.startActivity(i);



            }
        });



    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}