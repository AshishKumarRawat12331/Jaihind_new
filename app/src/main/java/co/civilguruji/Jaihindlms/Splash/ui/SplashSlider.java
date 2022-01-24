package co.civilguruji.Jaihindlms.Splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import co.civilguruji.Jaihindlms.Login.ui.LoginActivity;
import co.civilguruji.Jaihindlms.R;
import me.relex.circleindicator.CircleIndicator;


public class SplashSlider extends AppCompatActivity {
    ViewPager viewpager;
    CircleIndicator indicator;
  //  ImageView ivLeft, ivRight;
    TextView tvFinish;
    int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_slider);


        viewpager = (ViewPager)findViewById(R.id.viewpager);
        indicator = (CircleIndicator)findViewById(R.id.indicator);
      //  ivLeft = (ImageView) findViewById(R.id.ivLeft);
      //  ivRight = (ImageView) findViewById(R.id.ivRight);
        tvFinish = (TextView) findViewById(R.id.tvFinish);
        addTabs(viewpager);

      //  ivLeft.setVisibility(View.GONE);
      //  ivRight.setVisibility(View.VISIBLE);
     //   tvFinish.setVisibility(View.GONE);

        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
            }
        });

        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SplashSlider.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();


            }
        });

      /*  ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
            }
        });*/

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                pos = i;
                if (pos == 0){
                 //   ivLeft.setVisibility(View.GONE);
                   // ivRight.setVisibility(View.VISIBLE);
                    tvFinish.setVisibility(View.GONE);
                }else if (pos == 1){
                   // ivLeft.setVisibility(View.VISIBLE);
                    // ivRight.setVisibility(View.VISIBLE);
                    tvFinish.setVisibility(View.GONE);
                }else if (pos == 2){
                  //  ivLeft.setVisibility(View.VISIBLE);
                  //  ivRight.setVisibility(View.GONE);
                    tvFinish.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
     //   adapter.addFrag(new OneFragment(), "One");
      //  adapter.addFrag(new TwoFragment(), "Two");
     //   adapter.addFrag(new ThreeFragment(), "Three");
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

