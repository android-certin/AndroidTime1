/**
 * Created by pmachado on 8/20/15.
 */
package com.ciandt.worldwonders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ciandt.worldwonders.model.User;

public class LoginActivity extends AppCompatActivity {
    private final int REQUEST_SIGNUP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Button btnSignUp = (Button) findViewById(R.id.login_btn_signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);

            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            switch (requestCode) {
                case REQUEST_SIGNUP:
                User user = (User) data.getSerializableExtra("user");
                EditText edtUsername = (EditText) findViewById(R.id.login_edit_username);
                edtUsername.setText(user.getUsername());
                break;
            }
        }
    }
}
