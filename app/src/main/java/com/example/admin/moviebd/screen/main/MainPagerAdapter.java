package com.example.admin.moviebd.screen.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.moviebd.R;
import com.example.admin.moviebd.screen.movie.MovieFragment;
import com.example.admin.moviebd.screen.television.TelevisionFragment;

import static com.example.admin.moviebd.screen.main.TabType.MOVIE_FRAGMENT;
import static com.example.admin.moviebd.screen.main.TabType.TELEVISION_FRAGMENT;
import static com.example.admin.moviebd.screen.main.TabType.TAB_NUMBER_FRAGMENT;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MOVIE_FRAGMENT:
                return MovieFragment.newInstance();
            case TELEVISION_FRAGMENT:
                return TelevisionFragment.newInstance();
            default:
                return MovieFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return TAB_NUMBER_FRAGMENT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case MOVIE_FRAGMENT:
                return mContext.getString(R.string.movie);
            case TELEVISION_FRAGMENT:
                return mContext.getString(R.string.television);
            default:
                return null;
        }
    }
}
