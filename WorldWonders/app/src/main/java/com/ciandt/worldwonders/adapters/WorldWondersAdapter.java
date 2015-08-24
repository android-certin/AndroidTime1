package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.ui.fragments.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfranco on 8/23/15.
 */
public class WorldWondersAdapter extends FragmentPagerAdapter {
    int pageCount;
    List<String> listaUrls = new ArrayList<>();

    public WorldWondersAdapter(FragmentManager fragmentManager, int pageCount) {

        super(fragmentManager);
        this.pageCount = pageCount;
        getListaUrls();
    }

    @Override
    public Fragment getItem(int position) {
        Wonder wonder = new Wonder();
        wonder.setPhoto(listaUrls.get(position));
        HighlightFragment wonderFragment = HighlightFragment.newInstance(wonder);

        return wonderFragment;
    }

    public List<String> getListaUrls() {
        listaUrls.add(0, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Kheops-Pyramid.jpg/1024px-Kheops-Pyramid.jpg");
        listaUrls.add(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Hanging_Gardens_of_Babylon.jpg/350px-Hanging_Gardens_of_Babylon.jpg");
        listaUrls.add(2, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Statue_of_Zeus.jpg/220px-Statue_of_Zeus.jpg");
        return listaUrls;
    }

    @Override
    /**
     * Quantidade de páginas
     */
    public int getCount() {
        return this.pageCount;
    }
}