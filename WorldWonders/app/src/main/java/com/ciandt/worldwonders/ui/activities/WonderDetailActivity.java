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
    WondersRepository repository;
    MenuItem bookmarkMenuItem;
    private Menu menu;
    Bookmark bookmark;

    private ShareActionProvider mShareActionProvider;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        wonder = (Wonder) getIntent().getSerializableExtra(EXTRA_WONDER);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(wonder.getName());

        TextView descriptionWonder = (TextView) findViewById(R.id.detail_description_wonder);
        descriptionWonder.setText(wonder.getDescription());

        setImageOnDetail();
    }

    private void setImageOnDetail() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        getMenuInflater().inflate(R.menu.menu_main, this.menu);

        checkWonderDirections();

        checkWonderHasBookmark();

        return true;
    }

    private void checkWonderDirections() {
        MenuItem directionsItem = menu.findItem(R.id.action_direction);
        if (wonder.getLatitude() == 0.0 && wonder.getLongitude() == 0.0) {
            directionsItem.setVisible(false);
        }
    }

    private void checkWonderHasBookmark() {
        bookmarkMenuItem = menu.findItem(R.id.action_bookmark);
        repository = new WondersRepository(this);
        repository.getBookmarkByWonder(wonder.getId(), new WondersRepository.BookmarkByWonderListener() {
            @Override
            public void onBookmarkByWonder(Exception exception, Bookmark bookmark) {
                checkBookmarkOnWonder(bookmark);
            }
        });

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(bookmarkMenuItem);
    }

    private void checkBookmarkOnWonder(Bookmark bookmark) {
        if (bookmark != null) {
            this.bookmark = bookmark;
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_share:
                shareAction();
            break;

            case R.id.action_bookmark:
                bookmarkAction();

            break;

            case R.id.action_direction:
                directionAction();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareAction() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, wonder.getDescription());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void bookmarkAction() {
        repository = new WondersRepository(this);
        if(bookmark == null) {
            insertBookmark();
        } else {
            removeBookmark();
        }
    }

    private void insertBookmark() {
        bookmark = new Bookmark();
        bookmark.setIdWonders(wonder.getId());
        repository.insertBookmark(bookmark, new WondersRepository.BookmarkInsertListener() {
            @Override
            public void onBookmarkInsert(Exception exception, Boolean result) {
                isInserted(result);
            }
        });
    }

    private void isInserted(Boolean result) {
        if(result) {
            Toast.makeText(this, "Bookmark salvo com sucesso.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        } else {
            Toast.makeText(this, "Erro ao salvar o Bookmark.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_border_white_24dp);
        }
    }

    private void removeBookmark() {
        repository.deleteBookmark(bookmark, new WondersRepository.BookmarkDeleteListener() {
            @Override
            public void onBookmarkDelete(Exception exception, Boolean result) {
                isDeleted(result);
            }
        });
    }

    private void isDeleted(Boolean result) {
        if(result) {
            Toast.makeText(this, "Bookmark removido com sucesso.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_border_white_24dp);
        } else {
            Toast.makeText(this, "Erro ao remover o Bookmark.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        }
    }

    private void directionAction() {
        //Usar String Builder
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ wonder.getLatitude() + "," +  wonder.getLongitude());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setData(gmmIntentUri);

        if(mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            mapIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
        }
    }
}
