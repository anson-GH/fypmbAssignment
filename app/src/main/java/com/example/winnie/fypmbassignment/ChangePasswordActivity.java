package com.example.winnie.fypmbassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {


    Button butSubmit;
    EditText etCurrentPassword,etConfirmPassword,etNewPassword;
    UserLocalStore userLocalStore;
    User user ;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etCurrentPassword = (EditText) findViewById(R.id.etCurrentPassword);
        butSubmit = (Button) findViewById(R.id.butSubmit);

        userLocalStore = new UserLocalStore(this);
        user = userLocalStore.getLoggedInUser();


        butSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
              String current = etCurrentPassword.getText().toString();
              String confirm = etConfirmPassword.getText().toString();
              String newpw = etNewPassword.getText().toString();

              if (!current.equals(user.password))
                  etCurrentPassword.setError("Please enter current password");

              if (!confirm.equals(newpw)) {
                  etConfirmPassword.setError("confirm password not match");
                  etNewPassword.setError("new password not match");
              }
              if (confirm.equals(newpw)) {
                  etConfirmPassword.setError(null);
                  etNewPassword.setError(null);
              }
              if (current.equals(user.password))
                  etCurrentPassword.setError(null);

              if (current.equals(user.password) && confirm.equals(newpw)) {
                  user = new User(user.username, newpw, user.email, user.firebaseid);
             //     registerUser(user);
                  Toast.makeText(ChangePasswordActivity.this, "updated ", Toast.LENGTH_LONG).show();

              }

          }
        });
    }

//    private void registerUser(User user) {
//        ServerRequests serverRequests = new ServerRequests(this);
//        serverRequests.storeUserDataInBackground(user, new GetUserCallBack() {
//            @Override
//            public void done(User returnedUser) {
//              //  startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            }
//        });
//    }



}
