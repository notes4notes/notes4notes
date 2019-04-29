package com.example.notes4notes;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    /** Debug Tag */
    private static final String TAG = "LoginActivity";

    // UI References

    /** Username */
    private EditText username;
    /** Password */
    private EditText password;
    /**Login Button*/
    private Button loginBtn;
    /**Register Button */
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(persistenceCheck())
            afterLogInActivity();
        setContentView(R.layout.activity_login);
        bind();

    }// OnCreate


    /** Used to Asynchronously Login the User */
    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                //TO-DO: Useful Exception Handling
                if (e != null) {
                    Log.e(TAG, "Issue with Login");
                    e.printStackTrace();
                    return;
                }
                afterLogInActivity();
            } //done
        });
    } // end of method login

    /**
     * For changing Activities after Logging In
     */
    private void afterLogInActivity() {
        Log.d(TAG, "Navigating to activity after logging in");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    } //end of method afterLogInActivity

    /**
     * Checks whether someone signed in on the device and is still signed in after the last application closure
     */
    private boolean persistenceCheck() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        return (currentUser != null);
    } //end of method persistenceCheck()

    /**
     * Binds UI references
     */
    private void bind(){
        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        Button loginBtn    = findViewById(R.id.loginSignInButton  );
        Button registerBtn = findViewById(R.id.loginRegisterButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                login(usernameTxt, passwordTxt);
                // to do, remove later
                afterLogInActivity();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(i);
                //    finish()
                // Decide whether the login Activity should be killed or not.
            }
        });

    } // end of method bind


} //end of class LoginActivity