package com.ciandt.worldwonders.ui.fragments;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ciandt.worldwonders.R;

import java.util.zip.Inflater;

/**
 * Created by andersonr on 27/08/15.
 */
public class LoadingFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = "LoadingFragment";

    private ImageView cameraImageView;
    private ImageView eiffelTowerImageView;
    private ImageView suitcaseImageView;
    private ImageView pyramidsImageView;
    private ImageView tajMahalImageView;

    public static DialogFragment show(FragmentManager fragmentManager) {
        LoadingFragment fragment = new LoadingFragment();
        fragment.show(fragmentManager, FRAGMENT_TAG);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_loading, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
            .setView(view)
            .create();

        alertDialog.setCancelable(false);

        cameraImageView = (ImageView)view.findViewById(R.id.image_camera);
        eiffelTowerImageView = (ImageView)view.findViewById(R.id.image_eiffel_tower);
        pyramidsImageView = (ImageView)view.findViewById(R.id.image_pyramids);
        suitcaseImageView = (ImageView)view.findViewById(R.id.image_suitcase);
        tajMahalImageView = (ImageView)view.findViewById(R.id.image_taj_mahal);

        startAnimation(cameraImageView);
        startAnimation(eiffelTowerImageView);
        startAnimation(pyramidsImageView);
        startAnimation(suitcaseImageView);
        startAnimation(tajMahalImageView);

        return alertDialog;
    }

    private static void startAnimation(ImageView imageView) {
        AnimationDrawable cameraAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        cameraAnimationDrawable.start();
    }
}
