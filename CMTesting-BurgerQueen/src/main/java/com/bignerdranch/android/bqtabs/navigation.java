package com.bignerdranch.android.bqtabs;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class navigation extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private CartFragment cartFragment;

    public BottomNavigationView getmMainNav() {
        return mMainNav;
    }

    public FrameLayout getmMainFrame() {
        return mMainFrame;
    }

    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public OrderFragment getOrderFragment() {
        return orderFragment;
    }

    public CartFragment getCartFragment() {
        return cartFragment;
    }

    public ViewPager getViewPagerbnv() {
        return viewPagerbnv;
    }

    public RelativeLayout getMealLayout() {
        return mealLayout;
    }

    private ViewPager viewPagerbnv;
    private RelativeLayout mealLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Bundle extras = getIntent().getExtras();
        boolean openhome = false;
        if (extras != null && extras.containsKey("homefrag")) {
            openhome = extras.getBoolean("homefrag");

            if (openhome == true) {
                homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, homeFragment);
                fragmentTransaction.commit();
            }
        }
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);


        orderFragment = new OrderFragment();
        cartFragment = new CartFragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment, getResources().getString(R.string.nav_home));
                        return true;

                    case R.id.nav_neworders:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(orderFragment, getResources().getString(R.string.nav_order));
                        return true;

                    case R.id.newfavorites:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(cartFragment, getResources().getString(R.string.nav_cart));
                        return true;

                    default:
                        return false;
                }
            }

            private void setFragment(Fragment fragment, String tag) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment, tag);
                fragmentTransaction.commit();
            }
        });
    }
}




