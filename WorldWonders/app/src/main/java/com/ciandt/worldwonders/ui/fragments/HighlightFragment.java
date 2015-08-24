package com.ciandt.worldwonders.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by jfranco on 8/21/15.
 */
public class HighlightFragment extends Fragment {

    private View view;
    private static final String EXTRA_WONDER = "wonder";

    public static HighlightFragment newInstance(Wonder wonder) {
        HighlightFragment wonderFragment =  new HighlightFragment();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(EXTRA_WONDER, wonder);
        wonderFragment.setArguments(bundle);
        return wonderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_highlight, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView textView =  (TextView)view.findViewById(R.id.textView);

        Bundle arguments = getArguments();

        if (arguments != null) {
            Wonder wonder = (Wonder) arguments.getSerializable(EXTRA_WONDER);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

            Picasso.with(view.getContext())
                    .load(getRawResourceID(view.getContext(), wonder.getPhoto().replace(".jpg", "")))
                    .config(Bitmap.Config.RGB_565)
                    .into(imageView);

            textView.setText(wonder.getName());

        }
    }


    public static int getRawResourceID(Context context, String rawResourceName) {
        return context.getResources().getIdentifier(rawResourceName, "raw", context.getPackageName());
    }
}
