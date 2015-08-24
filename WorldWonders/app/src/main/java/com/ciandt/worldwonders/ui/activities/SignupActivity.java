package com.ciandt.worldwonders.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.User;

/**
 * Created by eferraz on 21/08/15.
 */
public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnSignup = (Button) findViewById(R.id.signup_btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edtUsername = (EditText) findViewById(R.id.signup_edit_username);

                User user = new User();
                user.setUsername(edtUsername.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("user", user);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
