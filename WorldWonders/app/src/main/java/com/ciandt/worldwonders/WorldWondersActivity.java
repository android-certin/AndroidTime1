package com.ciandt.worldwonders;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ciandt.worldwonders.adapters.WorldWondersAdapter;

/**
 * Created by eferraz on 21/08/15.
 */
public class WorldWondersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_world_wonders);
    }
}
