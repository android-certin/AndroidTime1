package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.fragments.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by jfranco on 8/23/15.
 */
public class WorldWondersAdapter extends FragmentPagerAdapter {
    int pageCount;

    public WorldWondersAdapter(FragmentManager fragmentManager, int pageCount) {

        super(fragmentManager);
        this.pageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        HighlightFragment wonderFragment = HighlightFragment.newInstance(new Wonder());

        return wonderFragment;
    }

    @Override
    /**
     * Quantidade de páginas
     */
    public int getCount() {
        return this.pageCount;
    }
}