/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.fragments.LoginFragment;
import com.ciandt.worldwonders.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_login, loginFragment).commit();

    }

}
