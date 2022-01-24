package co.civilguruji.Jaihindlms.Splash.dto;

import android.content.Context;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import co.civilguruji.Jaihindlms.R;


/**
 * it display the list of Images at start of app
 */


public class SplashSliderPagerAdapter extends PagerAdapter {

    int[] mResources = {

            R.drawable.a,
            R.drawable.b,
            R.drawable.a,
            R.drawable.b,
    };

    Context mContext;
    LayoutInflater mLayoutInflater;

    public SplashSliderPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}