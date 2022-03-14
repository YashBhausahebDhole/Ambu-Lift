package com.example.ambu_lift;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        tablayout.setupWithViewPager(viewPager);


        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragment1(), "EMERGENCY");
        vpAdapter.addFragment(new fragment2(), "PATIENT");
        vpAdapter.addFragment(new fragment3(), "AMBULANCE");
        viewPager.setAdapter(vpAdapter);


    }


}