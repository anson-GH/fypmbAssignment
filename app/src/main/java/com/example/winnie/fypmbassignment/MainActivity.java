package com.example.winnie.fypmbassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button bLogout,bChange;
    EditText etUsername, etPassword, etAge, etName, etMail;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etEmail);
        bLogout = (Button) findViewById(R.id.bLogout);
        bChange = (Button) findViewById(R.id.bChange);

        bLogout.setOnClickListener(this);
        bChange.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();

        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etPassword.setText(user.password);
        etName.setText(user.firebaseid);
        etMail.setText(user.email);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.bChange:
                startActivity(new Intent(this, ChangePasswordActivity.class));
        }


    }
}
