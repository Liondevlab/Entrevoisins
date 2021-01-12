package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public boolean mIsFavorite;
    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            mIsFavorite = false;
        } else {
            mIsFavorite = true;
        }
        return NeighbourFragment.newInstance(mIsFavorite);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}