package com.ciandt.worldwonders.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.adapters.WorldWondersAdapter;
import com.ciandt.worldwonders.database.WonderDao;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfranco on 8/24/15.
 */
public class WorldWondersFragment extends Fragment {

    private final static String WORLD_WONDERS = "wonders";

    public static WorldWondersFragment newInstance(ArrayList<Wonder> listWonder) {
        WorldWondersFragment wonderFragment =  new WorldWondersFragment();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(WORLD_WONDERS, listWonder);
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
        ArrayList<Wonder> listWonder = (ArrayList) getArguments().getSerializable(WORLD_WONDERS);
        WorldWondersAdapter worldWondersAdapter = new WorldWondersAdapter(getFragmentManager(), listWonder);
        viewPager.setAdapter(worldWondersAdapter);

    }
}
