package com.example.comp.moviesapp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.comp.moviesapp.constants.Constants;
import com.example.comp.moviesapp.ui.all_movies.AllMoviesFragment;
import com.example.comp.moviesapp.ui.movies_watchlist.MovieWatchlistFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 10.1.2018..
 */

public class PagerAdapter extends FragmentPagerAdapter {

    List<String> tabTitles = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addTitles(List<String> titles){
        tabTitles.addAll(titles);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return MovieWatchlistFragment.newInstance();
            case 1:
                return AllMoviesFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Constants.FRAGMENT_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
