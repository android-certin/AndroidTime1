package com.ciandt.worldwonders.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by jfranco on 8/26/15.
 */
public class WonderDetailActivity extends BaseActivity {
    private final String EXTRA_WONDER = "wonder";
    private Wonder wonder;

    private ShareActionProvider mShareActionProvider;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        wonder = (Wonder) getIntent().getSerializableExtra(EXTRA_WONDER);

//        supportPostponeEnterTransition();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(wonder.getName());
//        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));

        TextView descriptionWonder = (TextView) findViewById(R.id.detail_description_wonder);
        descriptionWonder.setText(wonder.getDescription());

        ImageView imageView = (ImageView) findViewById(R.id.detail_image_wonder);

//        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_WONDER);

        Picasso.with(getApplicationContext())
                .load(Helpers.getRawResourceID(getApplicationContext(), wonder.getPhoto().split("\\.")[0]))
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.raw.place_holder)
                .error(R.raw.place_holder)
                .centerCrop()
                .resize(250, 165)
                .into(imageView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()) {
            case R.id.action_share :
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, wonder.getDescription());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            break;

            case R.id.action_bookmark:
                Bookmark bookmark = new Bookmark();
                bookmark.setIdWonders(wonder.getId());
                WondersRepository repository = new WondersRepository(this);
                repository.insertBookmark(bookmark, new WondersRepository.BookmarkInsertListener() {
                    @Override
                    public void onBookmarkInsert(Exception exception, Boolean result) {
                        isInserted(result);
                    }
                });

            break;

            case R.id.action_direction:
                if(wonder.getLatitude() != 0.0 && wonder.getLongitude() != 0.0) {
                    Uri gmmIntentUri = Uri.parse("geo:"+ wonder.getLatitude() + "," +  wonder.getLongitude());

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    startActivity(mapIntent);
                } else {
                    Toast.makeText(this, "Monumento não possui latitude, longitude cadastrada.", Toast.LENGTH_SHORT).show();
                }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void isInserted(Boolean result) {
        if(result) {
            Toast.makeText(this, "Bookmark salvo com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar o Bookmark.", Toast.LENGTH_SHORT).show();
        }
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_bookmark);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

}
