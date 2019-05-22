package com.bignerdranch.android.bqtabs;


import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    public static BurgerQueenDBHelper BurgerQueenDB = null;
    public OrderList mOrderList;
    View v;
    private RecyclerView orderrecyclerview;
    private List<MenuItem> mData;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private ViewPager viewPagerbnv;
    private RelativeLayout mealLayout;
    Timer timer;
    private  int[] mImageIds = new int[] {R.drawable.smnelse, R.drawable.special2};

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        display(v);

        return v;
    }

    private void display(View v) {

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout_idfavorites);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager_idfavorites);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        // This is where each fragment is added
        adapter.AddFragment(new fragmentFavorites(), this.getString(R.string.favorites));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        viewPagerbnv = (ViewPager) v.findViewById(R.id.viewPaperfavorites);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPagerbnv.setAdapter(adapter);

        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPagerbnv.post(new Runnable() {

                    @Override
                    public void run() {
                        viewPagerbnv.setCurrentItem((viewPagerbnv.getCurrentItem() + 1) % mImageIds.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);
    }
    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BurgerQueenDB = new BurgerQueenDBHelper(getActivity());

    }


}
