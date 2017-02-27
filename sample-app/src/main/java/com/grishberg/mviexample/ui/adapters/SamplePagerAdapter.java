package com.grishberg.mviexample.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.grishberg.mviexample.ui.fragments.FirstFragment;
import com.grishberg.mviexample.ui.fragments.SecondFragment;

/**
 * Created by grishberg on 24.01.17.
 */
public class SamplePagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 10;

    public SamplePagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position % 2 == 0) {
            return FirstFragment.newInstance(position);
        }
        return SecondFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }
}
