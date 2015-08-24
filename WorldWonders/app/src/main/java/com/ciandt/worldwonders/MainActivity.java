/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.ciandt.worldwonders.fragments.LoginFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment loginFragment =  new LoginFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_login, loginFragment, "login")
                .commit();
    }

}
