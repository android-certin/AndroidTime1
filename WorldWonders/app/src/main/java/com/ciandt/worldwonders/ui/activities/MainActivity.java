/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders.ui.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.ui.fragments.LoginFragment;
import com.ciandt.worldwonders.ui.fragments.WorldWondersFragment;
import com.ciandt.worldwonders.model.Wonder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoginFragment();
    }

    private void initLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment loginFragment =  new LoginFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_login, loginFragment, "login")
                .commit();

        loginFragment.setOnLoginListener(new LoginFragment.OnLoginListener() {
            @Override
            public void onLogin(Wonder wonder) {
                initWondersFragment();
            }
        });
    }

    private void initWondersFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        WorldWondersFragment worldWondersFragment =  WorldWondersFragment.newInstance(new Wonder());

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_login, worldWondersFragment, "login")
                .commit();
    }

}