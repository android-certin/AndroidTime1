package com.ciandt.worldwonders.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.adapters.WorldWondersAdapter;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by jfranco on 8/24/15.
 */
public class WorldWondersFragment extends Fragment {

    private final int NUMBER_OF_PAGES = 3;
    private final static String WORLD_WONDERS = "wonder";

    public static WorldWondersFragment newInstance(Wonder wonder) {
        WorldWondersFragment wonderFragment =  new WorldWondersFragment();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(WORLD_WONDERS, wonder);
        wonderFragment.setArguments(bundle);
        return wonderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world_wonders, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager_wonder);

        WorldWondersAdapter worldWondersAdapter = new WorldWondersAdapter(getFragmentManager(), NUMBER_OF_PAGES);
        viewPager.setAdapter(worldWondersAdapter);

    }
}
