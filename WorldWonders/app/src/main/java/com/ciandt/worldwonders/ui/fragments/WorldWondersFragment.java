package com.ciandt.worldwonders.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.ciandt.worldwonders.repository.WondersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by jfranco on 8/24/15.
 */
public class WorldWondersFragment extends Fragment {

    private final static String WORLD_WONDERS = "wonders";
    ViewPager viewPager;

    public static WorldWondersFragment newInstance() {
        return new WorldWondersFragment();
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
        viewPager = (ViewPager)view.findViewById(R.id.pager_wonder);
        ArrayList<Wonder> listWonder;
        WondersRepository repository = new WondersRepository(view.getContext());
        repository.getAll(new WondersRepository.WonderAllListener() {
            @Override
            public void onWonderAll(Exception exception, List<Wonder> wonders) {
                createHighlight(wonders);
            }
        });


    }

    public void createHighlight(List<Wonder> wonders) {

        Collections.shuffle(wonders);
        WorldWondersAdapter worldWondersAdapter = new WorldWondersAdapter(getFragmentManager(), (ArrayList) wonders);
        viewPager.setAdapter(worldWondersAdapter);
    }
}
