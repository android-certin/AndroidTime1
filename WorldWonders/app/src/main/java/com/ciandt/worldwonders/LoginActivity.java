/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGINACTIVITY = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOGINACTIVITY, "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOGINACTIVITY, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOGINACTIVITY, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOGINACTIVITY, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOGINACTIVITY, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOGINACTIVITY, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOGINACTIVITY, "onRestart() called");
    }
}
