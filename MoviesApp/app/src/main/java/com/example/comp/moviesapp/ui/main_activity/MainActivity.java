package com.example.comp.moviesapp.ui.main_activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.ui.adapters.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by COMP on 10.1.2018..
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_viewpager)
    ViewPager filmViewPager;

    PagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {
        ButterKnife.bind(this);

        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Watchlist");
        tabTitles.add("Find movies");

        adapterViewPager = new PagerAdapter(getSupportFragmentManager());
        adapterViewPager.addTitles(tabTitles);
        filmViewPager.setAdapter(adapterViewPager);

        filmViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                filmViewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
