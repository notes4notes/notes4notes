package com.example.notes4notes.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notes4notes.Models.Post;
import com.example.notes4notes.Models.UploadedFiles;
import com.example.notes4notes.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ComposeFragment extends AbstractFragment {
    private static final String TAG = "Compose Fragment";
    private EditText composeTitle;
    private EditText composeDescription;
    private EditText composeTagClass;
    private static final int FILE_SELECT_CODE = 15; // Arbitrary
    private String filePath;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose, container, false);
    } // end of onCreateView
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bind (view);
    } // end of onViewCreated


    private void bind(View view){
        composeTitle         =  view.findViewById(R.id.composeTitle);
        composeDescription   =  view.findViewById(R.id.composeDescription);
        composeTagClass      =  view.findViewById(R.id.composeTagClass);
        Button composeSubmitBtn = view.findViewById(R.id.composeSubmitBtn);
        ImageButton composeAttachFileBtn = view.findViewById(R.id.composeAttachFileBtn);

        composeAttachFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        composeSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    } // end of bind

    private void submitPost(){

        String       postTitle        = composeTitle.getText().toString();
        String       postDescription  = composeDescription.getText().toString();
        String       postTags         = composeTagClass.getText().toString();
        ParseObject  postAuthor       = ParseUser.getCurrentUser();
        Boolean     existenceCheck    = checkSubmission(postTitle, postDescription);
        ParseFile    parseFile = null;
        String fileName = "alpha.png";
        ParseObject post = new Post();
        ParseObject fileObj =  ParseObject.create("UploadedFiles"); // new UploadedFiles(); //

        byte[] data = null;
        if(existenceCheck) {
            try {
                data = FileUtils.readFileToByteArray(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Log.e(TAG, "File Exists: " + String.valueOf((new File(filePath)).exists()));
                parseFile = new ParseFile(fileName, data);
            }
        }

        if (filePath != null && existenceCheck){
            Log.e(TAG, filePath);
            fileObj.put(UploadedFiles.getKeyAssociatedPost(), post);
            fileObj.put(UploadedFiles.getKeyUploader(), postAuthor);
            fileObj.put(UploadedFiles.getKeyUploadedFiles(), parseFile);
            fileObj.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null){
                        Log.e(TAG, "Error While uploading file");
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error While uploading file", Toast.LENGTH_SHORT).show();
                    }
                }// done
            });
        } else
            Toast.makeText(getContext(), "No File Selected", Toast.LENGTH_SHORT).show();

        if (existenceCheck){
            try {
                post.put(Post.getKeyPostUser(), postAuthor);
                post.put(Post.getKeyPostTitle(), postTitle);
                post.put(Post.getKeyPostDescription(), postDescription);
                post.put(Post.getKeyPostClass(), postTags);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            post.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null){
                        Log.e(TAG, "Error While submitting post");
                        Toast.makeText(getContext(), "Error While Submitting Post", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.e(TAG, "Post Success!");
                    clearComposeScreen();
                    Toast.makeText(getContext(), "Successfully submitted Post!", Toast.LENGTH_SHORT).show();
                }// done
            });
        } // end if


    } // end of submit post
    private boolean checkSubmission(String postTitle, String postDescription){
        Context context = this.getContext();
        int duration = Toast.LENGTH_SHORT;
        boolean a = postTitle.equals("");
        boolean b = postDescription.equals("");
        if (a){
            Toast.makeText(context,  "Please Enter A Post Title",  duration).show();
        }
        if (b){
            Toast.makeText(context,  "Please Enter A Description",  duration).show();
        }
        return !a && !b;
    }
    private void clearComposeScreen(){
        composeTitle.setText("");
        composeDescription.setText("");
        composeTagClass.setText("");
    } // end of method clearComposeScreen
    private void uploadFile (){ }

    /**
     * Allows the user to select data from the file system
     * The file is referenced by the URL
     * All files types are allowed at the moment
     * The method will:
     * Open the built-in file explorer if available, else
     * Prompt the user to select a file explorer.
     **/
    private void selectFile(){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    } // end of selectFile() method.

    /**
     * After the user has made a selection , do something based on result code.
     **/
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(data == null){
                Toast.makeText(getContext(), "No Selection Made!", Toast.LENGTH_SHORT).show();
                return;
            }
            /*Control Loop*/
            if(requestCode == FILE_SELECT_CODE){
                generateSelectedFileURI(data);
            }
        } // endif
    } // end of method onActivityResult()

    /**
     * @param data intent
     */
    private void generateSelectedFileURI(Intent data){
        Uri selectedFileUri = data.getData();
        String realFilePath = null;
        Cursor cursor = null;
        Context context = getContext();
        assert selectedFileUri != null;
        String uriString = selectedFileUri.toString();

        if (uriString.startsWith("content://")) {

            try {
                String[] proj = { MediaStore.Images.Media.DATA };
                assert context != null;
                cursor = context.getContentResolver().query(selectedFileUri,  proj, null, null, null);
                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
               realFilePath = cursor.getString(column_index);
            } finally { Objects.requireNonNull(cursor).close(); }
        }
        if(realFilePath != null){
            filePath = realFilePath;
            Toast.makeText(context,"File Added Successfully!" + filePath,Toast.LENGTH_SHORT).show();
        }else{ Toast.makeText(context,"Invalid Path",Toast.LENGTH_SHORT).show(); }
    } // end of method generateSelectedFileURI

} // end of class


/*
        composeDescription.setText(selectedFilePath);
        Log.i(TAG,"Selected File Path:" + selectedFilePath);

cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(selectedFileUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    selectedFilePath = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
                else if (uriString.startsWith("file://")) {
            realFilePath = myFile.getName();
        }
 */

