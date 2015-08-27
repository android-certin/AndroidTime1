package com.ciandt.worldwonders.ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;
import com.ciandt.worldwonders.ui.fragments.UrlLinkFragment;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by andersonr on 27/08/15.
 */
public class WonderDetailFragment extends Fragment {
    private final String EXTRA_WONDER = "wonder";
    private Wonder wonder;
    WondersRepository repository;
    MenuItem bookmarkMenuItem;
    private Menu menu;
    Bookmark bookmark;
    private ShareActionProvider mShareActionProvider;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView descriptionWonder;
    private TextView linkWonder;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        descriptionWonder = (TextView) view.findViewById(R.id.detail_description_wonder);
        linkWonder = (TextView) view.findViewById(R.id.detail_link_wonder);
        imageView = (ImageView) view.findViewById(R.id.detail_image_wonder);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Bundle args = getArguments();
        if (args == null) {
            wonder = (Wonder) getActivity().getIntent().getSerializableExtra(EXTRA_WONDER);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            wonder = (Wonder) args.getSerializable(EXTRA_WONDER);
        }
        if (wonder != null) {
            setWonder(wonder);
        }
    }

    public void setWonder(final Wonder wonder) {
        this.wonder = wonder;
        collapsingToolbarLayout.setTitle(wonder.getName());
        descriptionWonder.setText(wonder.getDescription());
        linkWonder.setText(wonder.getUrl());
        linkWonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlLinkFragment.show(wonder, getFragmentManager());
            }
        });

        setImageOnDetail();
    }

    private void setImageOnDetail() {

        Picasso.with(getContext())
                .load(Helpers.getRawResourceID(getContext(), wonder.getPhoto().split("\\.")[0]))
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.raw.place_holder)
                .error(R.raw.place_holder)
                .centerCrop()
                .resize(250, 165)
                .into(imageView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;

        inflater.inflate(R.menu.menu_main, this.menu);

        checkWonderDirections();

        checkWonderHasBookmark();
    }

    private void checkWonderDirections() {
        MenuItem directionsItem = menu.findItem(R.id.action_direction);
        if (wonder.getLatitude() == 0.0 && wonder.getLongitude() == 0.0) {
            directionsItem.setVisible(false);
        }
    }

    private void checkWonderHasBookmark() {
        bookmarkMenuItem = menu.findItem(R.id.action_bookmark);
        repository = new WondersRepository(getContext());
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
        repository = new WondersRepository(getContext());
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
            Toast.makeText(getContext(), "Bookmark salvo com sucesso.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        } else {
            Toast.makeText(getContext(), "Erro ao salvar o Bookmark.", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(), "Bookmark removido com sucesso.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_border_white_24dp);
        } else {
            Toast.makeText(getContext(), "Erro ao remover o Bookmark.", Toast.LENGTH_SHORT).show();
            bookmarkMenuItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        }
    }

    private void directionAction() {
        //Usar String Builder
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ wonder.getLatitude() + "," +  wonder.getLongitude());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setData(gmmIntentUri);

        if(mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            mapIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
        }
    }
}
