package com.bignerdranch.android.bqtabs;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    public static BurgerQueenDBHelper BurgerQueenDB = null;
    View v;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private ViewPager viewPagerbnv;
    private RelativeLayout mealLayout;

    public OrderFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_order,container,false);
        display(v);

        return v;
    }

    private void display(View v) {

        tabLayout = (TabLayout) v.findViewById(R.id.tablayout_idorder);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager_idorder);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        // This is where each fragment is added
        adapter.AddFragment(new fragmentOrders(), this.getString(R.string.order));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BurgerQueenDB = new BurgerQueenDBHelper(getActivity());

    }


}
