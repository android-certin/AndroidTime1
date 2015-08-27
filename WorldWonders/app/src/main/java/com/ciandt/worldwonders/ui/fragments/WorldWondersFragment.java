package com.ciandt.worldwonders.ui.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.adapters.HighlightAdapter;
import com.ciandt.worldwonders.adapters.WorldWonderAdapter;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;
import com.ciandt.worldwonders.ui.activities.WonderDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jfranco on 8/24/15.
 */
public class WorldWondersFragment extends Fragment {

    ViewPager viewPager;
    RecyclerView recyclerView;
    ArrayList<Wonder> listWonder;
    LoadingFragment loadingFragment;
    WonderDetailFragment wonderDetailFragment;
    FrameLayout wonderDetailLayout;

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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager)view.findViewById(R.id.pager_wonder);
        WondersRepository repository = new WondersRepository(view.getContext());
        repository.getAll(new WondersRepository.WonderAllListener() {
            @Override
            public void onWonderAll(Exception exception, List<Wonder> wonders) {
                createWorldWonder(wonders, view);
                createHighlight(wonders);

                if (wonderDetailLayout != null) {
                    showDetailInFragment(wonders.get(0));
                }

                loadingFragment.dismiss();
            }
        });

        loadingFragment = (LoadingFragment) LoadingFragment.show(getFragmentManager());
        wonderDetailLayout = (FrameLayout) view.findViewById(R.id.fragment_detail);
    }

    private void showDetailInFragment(Wonder wonder) {
        if (wonderDetailFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            wonderDetailFragment = new WonderDetailFragment();

            Bundle args = new Bundle();
            args.putSerializable("wonder", wonder);
            wonderDetailFragment.setArguments(args);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_detail, wonderDetailFragment, "detail")
                    .commit();
        }
        else {
            wonderDetailFragment.setWonder(wonder);
        }

    }

    public void createHighlight(List<Wonder> wonders) {
        if (viewPager != null) {
            Collections.shuffle(wonders);
            HighlightAdapter highlightAdapter = new HighlightAdapter(getFragmentManager(), (ArrayList) wonders);
            viewPager.setAdapter(highlightAdapter);
        }
    }

    public void createWorldWonder (List<Wonder> wonders, View view) {
        WorldWonderAdapter adapter = new WorldWonderAdapter(this.getContext(), (ArrayList) wonders);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnSelectItem(new WorldWonderAdapter.OnSelectItem() {
            @Override
            public void onSelectItem(Wonder wonder) {
                if (wonderDetailLayout != null) {
                    showDetailInFragment(wonder);
                }
                else {
                    callWonderDetailActivity(wonder);
                }
            }
        });
    }

    private void callWonderDetailActivity(Wonder wonder) {
        Intent wonderDetail = new Intent(WorldWondersFragment.this.getActivity(), WonderDetailActivity.class);
        wonderDetail.putExtra("wonder", wonder);
        getActivity().startActivity(wonderDetail);
    }
}
