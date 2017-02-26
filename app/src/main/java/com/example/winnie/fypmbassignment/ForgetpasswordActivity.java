package com.example.winnie.fypmbassignment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetpasswordActivity extends ActionBarActivity implements View.OnClickListener {

    Button bSubmit;
    EditText etEmailRecovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        bSubmit = (Button) findViewById(R.id.bSubmit);
        etEmailRecovery = (EditText) findViewById(R.id.etEmailRecovery);


        bSubmit.setOnClickListener(this);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bSubmit:

                String email = etEmailRecovery.getText().toString();
                User user = new User(email, null, null);


                ServerRequestPassword serverRequestPassword = new ServerRequestPassword(this);
                serverRequestPassword.fetchUserDataInBackground(user, new GetUserCallBack() {
                    @Override
                    public void done(User user) {
                        if (user == null) {
                            etEmailRecovery.setError("Please enter valid password");

                            // Toast.makeText(ForgetpasswordActivity.this, "Invalid email!",Toast.LENGTH_LONG).show();
                        } else {

                            sendEmail(user.email, user.username);

                        }
                    }
                });


                break;

        }

    }

    public void sendEmail(String email, String username) {
        //Getting content for email
        String subject = "TAR University College Password Recovery";
        String message = username;
        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }


}
