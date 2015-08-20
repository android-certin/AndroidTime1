/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGIN_ACTIVITY = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOGIN_ACTIVITY, "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOGIN_ACTIVITY, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOGIN_ACTIVITY, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOGIN_ACTIVITY, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOGIN_ACTIVITY, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOGIN_ACTIVITY, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOGIN_ACTIVITY, "onRestart() called");
    }
}
