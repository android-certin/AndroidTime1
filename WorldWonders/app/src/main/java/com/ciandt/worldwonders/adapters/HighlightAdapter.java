package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.ui.fragments.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfranco on 8/23/15.
 */
public class HighlightAdapter extends FragmentPagerAdapter {
    private int pageCount;
    private final int LIMIT_PAGE = 3;
    List<Wonder> listWonder;

    public HighlightAdapter(FragmentManager fragmentManager, ArrayList<Wonder> listWonder) {

        super(fragmentManager);
        this.pageCount = LIMIT_PAGE;
        this.listWonder = listWonder;
    }

    @Override
    public Fragment getItem(int position) {
        HighlightFragment wonderFragment = HighlightFragment.newInstance(listWonder.get(position));
        return wonderFragment;
    }

    @Override
    public int getCount() {
        return this.pageCount;
    }
}