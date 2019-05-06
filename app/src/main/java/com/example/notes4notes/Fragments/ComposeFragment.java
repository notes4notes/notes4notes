package com.example.notes4notes.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.notes4notes.R;
import com.parse.ParseUser;

public class ComposeFragment extends AbstractFragment {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etClass;
    private Button btnSubmit;
    ProgressBar pb;
    File noteFile;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etTitle =  view.findViewById(R.id.etComposeTitle);
        etDescription = view.findViewById(R.id.etComposeDescription);
        etClass = view.findViewById(R.id.etComposeClass);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        //pb = view.findViewById(R.id.loading);
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                String description = etDescription.getText().toString();
                ParseUser user = ParseUser.getCurrentUser();
                //if(photoFile == null || ivPostImage.getDrawable() == null){
                    Toast.makeText(getContext(), "You did not put a photo!", Toast.LENGTH_SHORT).show();
                //}
                pb.setVisibility(ProgressBar.VISIBLE);
                //savePost(description, user, photoFile);
            }
        });
    }


}
