/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders.ui.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.database.WonderDao;
import com.ciandt.worldwonders.ui.fragments.LoginFragment;
import com.ciandt.worldwonders.ui.fragments.WorldWondersFragment;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addLoginFragment();
    }

    private void addLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment loginFragment =  new LoginFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_login, loginFragment, "login")
                .commit();

        loginFragment.setOnLoginListener(new LoginFragment.OnLoginListener() {
            @Override
            public void onLogin(Wonder wonder) {
                addWondersFragment();
            }
        });
    }

    private void addWondersFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        WorldWondersFragment worldWondersFragment =  WorldWondersFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_login, worldWondersFragment, "login")
                .commit();
    }

}
