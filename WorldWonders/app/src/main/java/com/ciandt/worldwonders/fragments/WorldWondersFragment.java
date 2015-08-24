package com.ciandt.worldwonders.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ciandt.worldwonders.R;

/**
 * Created by jfranco on 8/21/15.
 */
public class WorldWondersFragment extends Fragment {

    private View view;
    private static final String EXTRA_TEXT = "text";

    public static WorldWondersFragment openPageDynamic(String text) {
        WorldWondersFragment wonderFragment =  new WorldWondersFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(EXTRA_TEXT, text);
        wonderFragment.setArguments(bundle);
        return wonderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_world_wonders, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView textView =  (TextView)view.findViewById(R.id.textView);

        Bundle arguments = getArguments();

        if (arguments != null) {
            String text = arguments.getString(EXTRA_TEXT);
            textView.setText(text);
        }
    }
}
