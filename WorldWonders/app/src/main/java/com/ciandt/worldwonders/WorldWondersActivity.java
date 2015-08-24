package com.ciandt.worldwonders;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ciandt.worldwonders.adapters.WorldWondersAdapter;

/**
 * Created by eferraz on 21/08/15.
 */
public class WorldWondersActivity extends AppCompatActivity {

    private final int NUMBER_OF_PAGES = 3;
    private final int FIRST_PAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_wonders);

        ViewPager viewPager = (ViewPager)findViewById(R.id.pager_wonder);

        WorldWondersAdapter worldWondersAdapter = new WorldWondersAdapter(getSupportFragmentManager(), NUMBER_OF_PAGES);
        viewPager.setAdapter(worldWondersAdapter);
        viewPager.setCurrentItem(FIRST_PAGE);
    }
}
