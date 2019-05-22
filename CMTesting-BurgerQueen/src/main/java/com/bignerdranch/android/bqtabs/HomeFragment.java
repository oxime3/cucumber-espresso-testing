package com.bignerdranch.android.bqtabs;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class  HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Button button2;

    private ViewPager viewPagerbnv;
    private RelativeLayout mealLayout;
    Timer timer;
    private  int[] mImageIds = new int[] {R.drawable.smnelse, R.drawable.special2};
//R.drawable.garden_salad, R.drawable.spicy_chicken
//,R.drawable.burger_special2,
//    R.drawable.chicken_fries, R.drawable.chicken_wrap, R.drawable.dblqtrpndqueen, R.drawable.onion_rings, R.drawable.royal_fries, R.drawable.spicy_chicken,
//    R.drawable.chocolate_chip, R.drawable.coca_cola, R.drawable.fiji, R.drawable.garden_salad, R.drawable.hashbrowns


    public HomeFragment() {
        // Required empty public constructor
    }

    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        display(view);
        setHasOptionsMenu(true);
        return  view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu); //your file name
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(getActivity(), ActivitySearch.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.set_item:
                intent = new Intent(getActivity(), Settings.class);
                startActivityForResult(intent,1);
                return true;

        }
        return false;
    }





    private void display(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getChildFragmentManager());


        // This is where each fragment is added
        adapter.AddFragment(new fragmentBurger(), this.getString(R.string.burgers));
        adapter.AddFragment(new fragmentSides(), this.getString(R.string.sides));
        adapter.AddFragment(new fragmentDrink(), this.getString(R.string.drink));
        adapter.AddFragment(new fragmentDessert(), this.getString(R.string.dessert));



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        viewPagerbnv = (ViewPager) view.findViewById(R.id.viewPaper);
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


}

