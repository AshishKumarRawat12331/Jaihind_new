package co.civilguruji.Jaihindlms.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.BlogSubFragment;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.CategoriesSubFragment;
import co.civilguruji.Jaihindlms.Fragment.SubFragment.FavouriteSubFragment;
import co.civilguruji.Jaihindlms.R;

public class BlogFragment extends Fragment implements View.OnClickListener {

    LinearLayout Blog,Categories,Favourite;
    TextView tv_Favourite,tv_Categories,tv_Blog;
    View v1,v2,v3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_blog, container, false);

        GetId(v);

        return v;

    }

    private void GetId(View v) {

        Blog=v.findViewById(R.id.Blog);
        Categories=v.findViewById(R.id.Categories);
        Favourite=v.findViewById(R.id.Favourite);

        tv_Favourite=v.findViewById(R.id.tv_Favourite);
        tv_Categories=v.findViewById(R.id.tv_Categories);
        tv_Blog=v.findViewById(R.id.tv_Blog);

        v1=v.findViewById(R.id.v1);
        v2=v.findViewById(R.id.v2);
        v3=v.findViewById(R.id.v3);

        Blog.setOnClickListener(this);
        Categories.setOnClickListener(this);
        Favourite.setOnClickListener(this);

        tv_Blog.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        v1.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
        tv_Categories.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
        v2.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));
        tv_Favourite.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
        v3.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);

        changeFragment(new BlogSubFragment());

    }

    private void changeFragment(Fragment targetFragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onClick(View view) {

        if(view==Blog){

            tv_Blog.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            v1.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

            tv_Categories.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
            v2.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

            tv_Favourite.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
            v3.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.INVISIBLE);
            v3.setVisibility(View.INVISIBLE);

            changeFragment(new BlogSubFragment());
        }


         if(view==Categories){

             tv_Blog.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
             v1.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

             tv_Favourite.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
             v3.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

             tv_Categories.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
             v2.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

             v1.setVisibility(View.INVISIBLE);
             v2.setVisibility(View.VISIBLE);
             v3.setVisibility(View.INVISIBLE);

             changeFragment(new CategoriesSubFragment());
         }


         if(view==Favourite){

             tv_Blog.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
             v1.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

             tv_Favourite.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
             v3.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

             tv_Categories.setTextColor(getActivity().getResources().getColor(R.color.text_grey_light));
             v2.setBackgroundColor(getActivity().getResources().getColor(R.color.text_grey_light));

             v1.setVisibility(View.INVISIBLE);
             v2.setVisibility(View.INVISIBLE);
             v3.setVisibility(View.VISIBLE);

             changeFragment(new FavouriteSubFragment());

         }





    }
}