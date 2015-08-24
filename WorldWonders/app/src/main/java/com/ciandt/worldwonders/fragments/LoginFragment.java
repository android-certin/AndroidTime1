package com.ciandt.worldwonders.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.SignupActivity;
import com.ciandt.worldwonders.WorldWondersActivity;
import com.ciandt.worldwonders.model.User;

/**
 * Created by jfranco on 8/21/15.
 */
public class LoginFragment extends Fragment {

    private final int REQUEST_SIGNUP = 1;

   private EditText edtUsername;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = (EditText) view.findViewById(R.id.login_edit_username);

        signUpButtonAction(view);

        loginButtonAction(view);
    }

    private void loginButtonAction(View view) {
        Button btnLogin = (Button) view.findViewById(R.id.login_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFragment.this.getActivity(), WorldWondersActivity.class);
                startActivity(intent);

            }

        });
    }

    private void signUpButtonAction(View view) {
        Button btnSignUp = (Button) view.findViewById(R.id.login_btn_signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFragment.this.getActivity(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);

            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(intent != null) {
            switch (requestCode) {
                case REQUEST_SIGNUP:
                    User user = (User) intent.getSerializableExtra("user");
                    edtUsername.setText(user.getUsername());
                    break;
            }
        }
    }
}
