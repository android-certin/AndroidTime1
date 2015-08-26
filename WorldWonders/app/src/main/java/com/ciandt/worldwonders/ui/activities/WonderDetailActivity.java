package com.ciandt.worldwonders.ui.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by jfranco on 8/26/15.
 */
public class WonderDetailActivity extends BaseActivity {
    private final String EXTRA_WONDER = "wonder";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        Wonder wonder = (Wonder) getIntent().getSerializableExtra(EXTRA_WONDER);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(wonder.getName());

        TextView descriptionWonder = (TextView) findViewById(R.id.detail_description_wonder);
        descriptionWonder.setText(wonder.getDescription());

        ImageView imageView = (ImageView) findViewById(R.id.detail_image_wonder);

        Picasso.with(getApplicationContext())
                .load(Helpers.getRawResourceID(getApplicationContext(), wonder.getPhoto().split("\\.")[0]))
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.raw.place_holder)
                .error(R.raw.place_holder)
                .centerCrop()
                .resize(250, 165)
                .into(imageView);

    }
}
