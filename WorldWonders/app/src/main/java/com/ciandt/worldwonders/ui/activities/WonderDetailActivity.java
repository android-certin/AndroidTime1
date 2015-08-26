package com.ciandt.worldwonders.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.ciandt.worldwonders.R;


/**
 * Created by jfranco on 8/26/15.
 */
public class WonderDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail);
    }
}
