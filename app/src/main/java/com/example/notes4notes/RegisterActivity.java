package com.example.notes4notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes4notes.Models.User;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.File;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "Register Activity";
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPass;
    private Button btnSignUp;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bind();
    } // end of onCreate method

    void bind(){
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastName = etLastName.getText().toString();
                String firstName = etFirstName.getText().toString();
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();
                if (firstName.length() <= 0 || lastName.length() <= 0 || username.length() <= 0 || email.length() <= 0 || password.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "Missing a field", Toast.LENGTH_LONG).show();
                } else {

                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setEmail(email);
                    newUser.setPassword(password);
                    newUser.put(User.getKeyUserFirstName(), firstName);
                    newUser.put(User.getKeyUserLastName(),lastName);
                    File test = new File("test.jpg");
                    ParseFile bun = new ParseFile(test);
                    //newUser.put("userProfilePic", bun);
                    newUser.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(RegisterActivity.this, "Thanks for signing up", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed to sign up", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
} // end of class
