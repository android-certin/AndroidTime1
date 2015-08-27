package com.ciandt.worldwonders.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;
import com.ciandt.worldwonders.ui.fragments.UrlLinkFragment;
import com.ciandt.worldwonders.ui.fragments.WonderDetailFragment;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by jfranco on 8/26/15.
 */
public class WonderDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);
        addWonderDetailFragment();
    }

    private void addWonderDetailFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        WonderDetailFragment wonderDetailFragment =  new WonderDetailFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_detail, wonderDetailFragment, "detail")
                .commit();
    }
}
