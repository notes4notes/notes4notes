package com.example.notes4notes.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notes4notes.R;

import java.io.File;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ComposeFragment extends AbstractFragment {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etClass;
    private TextView tvFileName;
    private Button btnSubmit;
    private Button btnUpload;
    private String selectedFilePath;
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
    //    btnUpload = view.findViewById(R.id.btnComposeUpload);
    //    tvFileName = view.findViewById(R.id.tvComposeFileName);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //sets the select file to all types of files
                intent.setType("*/*");
                //allows to select data and return it
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //starts new activity to select file and return data
                startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),1);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTitle.setText("Title");
                etDescription.setText("Description");
                etClass.setText("Class");
                tvFileName.setText("File Selected: ");
                Toast.makeText(getContext(), "Successfully submitted Post!", Toast.LENGTH_SHORT).show();
            }
        });
        //pb = view.findViewById(R.id.loading);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1){
                if(data == null){
                    Toast.makeText(getContext(), "No file uploaded", Toast.LENGTH_SHORT).show();
                    return;
                }


                Uri selectedFileUri = data.getData();
                //selectedFilePath = FilePath.getPath(getContext(),selectedFileUri);
                String uriString = selectedFileUri.toString();
                File myFile = new File(uriString);
                String path = myFile.getAbsolutePath();
                String displayName = null;
                selectedFilePath = path;
                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = getActivity().getContentResolver().query(selectedFileUri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            //displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            selectedFilePath = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        }
                    } finally {
                        cursor.close();
                    }
                } else if (uriString.startsWith("file://")) {
                    //displayName = myFile.getName();
                    selectedFilePath = myFile.getName();
                }
                Log.i(TAG,"Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    tvFileName.setText(tvFileName.getText() + selectedFilePath);
                }else{
                    Toast.makeText(getContext(),"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
