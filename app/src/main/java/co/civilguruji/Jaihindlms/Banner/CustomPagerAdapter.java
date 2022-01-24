package co.civilguruji.Jaihindlms.Banner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import co.civilguruji.Jaihindlms.R;
import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;


/**
 * it display the list of Images at start of app
 */


public class CustomPagerAdapter extends PagerAdapter {

    private ArrayList<GalleryObject> ImageList;

    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(ArrayList<GalleryObject> ImageList, Context context) {
        this.ImageList = ImageList;
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        Log.e("Imageslider"," "+ApplicationConstant.INSTANCE.ImageBAseUrlSlider+"/"+ImageList.get(position).getImage());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        Glide.with(mContext)
                .load(ApplicationConstant.INSTANCE.ImageBAseUrlSlider+ImageList.get(position).getImage().replace("~",""))
                .apply(requestOptions)
                .into(imageView);

        container.addView(itemView);

        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}