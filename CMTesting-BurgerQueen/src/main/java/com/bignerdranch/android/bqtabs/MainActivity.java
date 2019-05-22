package com.bignerdranch.android.bqtabs;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // This is where each fragment is added
        adapter.AddFragment(new fragmentBurger(),"Burgers");
        adapter.AddFragment(new fragmentSides(),"Sides");
        adapter.AddFragment(new fragmentDrink(),"Drink");
        adapter.AddFragment(new fragmentDessert(),"Dessert");


        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


}
