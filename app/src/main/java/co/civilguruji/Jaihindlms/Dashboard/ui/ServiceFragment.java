package co.civilguruji.Jaihindlms.Dashboard.ui;

  import android.content.SharedPreferences;
  import android.graphics.Typeface;
  import android.os.Bundle;

  import androidx.fragment.app.Fragment;
  import androidx.recyclerview.widget.RecyclerView;
  import androidx.viewpager.widget.ViewPager;

  import android.os.Handler;
  import android.view.LayoutInflater;
  import android.view.MotionEvent;
  import android.view.View;
 import android.view.ViewGroup;
  import android.widget.ImageView;
  import android.widget.LinearLayout;
  import android.widget.TextView;

  import com.google.gson.Gson;

  import org.greenrobot.eventbus.EventBus;
  import org.greenrobot.eventbus.Subscribe;


  import java.util.ArrayList;

  import co.civilguruji.Jaihindlms.ApiRespose.DataLogin;
  import co.civilguruji.Jaihindlms.Banner.CustomPagerAdapter;
  import co.civilguruji.Jaihindlms.Banner.GalleryObject;
  import co.civilguruji.Jaihindlms.Banner.VideoGalleryResponse;
  import co.civilguruji.Jaihindlms.R;
  import co.civilguruji.Jaihindlms.Utils.ApplicationConstant;
  import co.civilguruji.Jaihindlms.Utils.FragmentActivityMessage;
  import co.civilguruji.Jaihindlms.Utils.GlobalBus;
  import co.civilguruji.Jaihindlms.Utils.Loader;
  import co.civilguruji.Jaihindlms.Utils.UtilMethods;

  import static android.content.Context.MODE_PRIVATE;


public class ServiceFragment extends Fragment implements View.OnClickListener {

    LinearLayout Functional_Area,cities,Industries;
    TextView Functional_areate,edit_type;

    RecyclerView recycler_view;

    Loader loader;
    ImageView heart_im;
    TextView city_name,search;
    LinearLayout filterView;

    ViewPager mViewPager;
    Handler handler;
    Integer mDotsCount;
    public static TextView mDotsText[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);


        GetId(v);


     return v;


    }

    private void GetId(View v) {

        mViewPager = (ViewPager)v.findViewById(R.id.pager);


        filterView=v.findViewById(R.id.filterView);
        city_name=v.findViewById(R.id.city_name);
        search=v.findViewById(R.id.search);
        search.setOnClickListener(this);

        heart_im=v.findViewById(R.id.heart_im);
        heart_im.setOnClickListener(this);
        filterView.setOnClickListener(this);



        Functional_areate=v.findViewById(R.id.Functional_areate);
        edit_type=v.findViewById(R.id.edit_type);

        edit_type.setOnClickListener(this);

        loader = new Loader(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);



        recycler_view=v.findViewById(R.id.recycler_view);

        Functional_Area=v.findViewById(R.id.Functional_Area);
        cities=v.findViewById(R.id.cities);
        Industries=v.findViewById(R.id.Industries);

        Functional_Area.setOnClickListener(this);
        cities.setOnClickListener(this);
        Industries.setOnClickListener(this);

        SharedPreferences myPreferences =  getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String FunctionalArea = myPreferences.getString(ApplicationConstant.INSTANCE.Functional_Area_id, "");
        String Functional_areatext = myPreferences.getString(ApplicationConstant.INSTANCE.Functional_area, "");
        String Functional_Area_job_list = myPreferences.getString(ApplicationConstant.INSTANCE.Functional_Area_job, "");

        if(FunctionalArea.equalsIgnoreCase("")){




        }else {

          /*  Intent intent = new Intent(getActivity(), FunctionalAreaActivity.class);
            intent.putExtra("type","Functional Areas");
            startActivity(intent);*/

            Functional_areate.setText("   "+ Functional_areatext+"   ");

            SetLogindeta();


        }

        GetUpdate();


    }


    private void GetUpdate() {
        if (UtilMethods.INSTANCE.isNetworkAvialable( getActivity())) {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);


            UtilMethods.INSTANCE.Banner( getActivity(), loader);

        } else {

        }
    }



    private void SetLogindeta() {

        SharedPreferences myPreferences =  getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, "");
        DataLogin balanceCheckResponse = new Gson().fromJson(response, DataLogin.class);

       // city_name.setText("  "+balanceCheckResponse.getCity()+"  ");

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    public void dataParse(String response) {


    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("jobslist")) {

            dataParse(activityFragmentMessage.getMessage());

        }

        if (activityFragmentMessage.getFrom().equalsIgnoreCase("videogallery")) {
            String response=activityFragmentMessage.getMessage();
            GalleryList(response);
        }


    }

    CustomPagerAdapter mCustomPagerAdapter;
    public ArrayList<GalleryObject> imageList = new ArrayList<>();
    VideoGalleryResponse GalleryList = new VideoGalleryResponse();



    private void GalleryList(String response) {

      /*  Gson gson = new Gson();
        GalleryList = gson.fromJson(response, VideoGalleryResponse.class);

        imageList = GalleryList.getList();*/


        GalleryList = new Gson().fromJson(response, VideoGalleryResponse.class);
        imageList = GalleryList.getData();

        // For Inflate Banner Images...
        if (imageList != null && imageList.size() > 0) {
            mCustomPagerAdapter = new CustomPagerAdapter(imageList, getActivity());
            mViewPager.setAdapter(mCustomPagerAdapter);

            mDotsCount = mViewPager.getAdapter().getCount();

            mDotsText = new TextView[mDotsCount];

            //here we set the dots
            for (int i = 0; i < mDotsCount; i++) {
                mDotsText[i] = new TextView(getActivity());
                mDotsText[i].setText(".");
                mDotsText[i].setTextSize(40);
                mDotsText[i].setTypeface(null, Typeface.BOLD);
                mDotsText[i].setTextColor(android.graphics.Color.GRAY);
                // dotsCount.addView(mDotsText[i]);
            }

            mViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    for (int i = 0; i < mDotsCount; i++) {
                        mDotsText[i].setTextColor(getResources().getColor(R.color.colorBackground));
                    }
                    mDotsText[position].setTextColor(getResources().getColor(R.color.black));
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            postDelayedScrollNext();
        }

    }

    private void postDelayedScrollNext() {
        handler.postDelayed(new Runnable() {
            public void run() {

                try {
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                        mViewPager.setCurrentItem(0);
                        postDelayedScrollNext();
                        return;
                    }
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    // postDelayedScrollNext(position+1);
                    postDelayedScrollNext();
                }catch (Exception e){

                }
                // onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
            }
        }, 5000);

    }






  @Override
 public void onClick(View view) {

        if(view==search){



        }

        if(view==filterView){



        }





   if(view==heart_im){



        }

       if(view==edit_type){



        }




   if(view==Functional_Area){



        }

        if(view==cities){



        }


        if(view==Industries){



        }

 }

}
