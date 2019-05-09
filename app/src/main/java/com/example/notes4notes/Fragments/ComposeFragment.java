package com.example.notes4notes.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.notes4notes.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ComposeFragment extends AbstractFragment {

    private EditText composeTitle;
    private EditText composeDescription;
    private EditText composeTagClass;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose, container, false);
    } // end of onCreateView

    private static final String TAG = "Compose Fragment";

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
                Intent intent = new Intent();
                //sets the select file to all types of files
                intent.setType("*/*");
                //allows to select data and return it
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //starts new activity to select file and return data
                startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),1);
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
        String postTitle        = composeTitle.getText().toString();
        String postDescription  = composeDescription.getText().toString();
        String postTags         = composeTagClass.getText().toString();
        ParseObject  postAuthor    = ParseUser.getCurrentUser();

        Log.d(TAG,postTitle);
        Log.d(TAG,postDescription);
        Log.d(TAG,postTags);
        Log.d(TAG,Post.getKeyPostUser());

        ParseObject post = new Post();

        if (checkSubmission(postTitle, postDescription)){
            try {
                post.put(Post.getKeyPostUser(), postAuthor);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            post.put(Post.getKeyPostTitle(), postTitle);
            post.put(Post.getKeyPostDescription(), postDescription);
            post.put(Post.getKeyPostClass(), postTags);

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
        }
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
        if (a || b)
                return false;
        else
            return true;
    }
    private void clearComposeScreen(){
        composeTitle.setText("");
        composeDescription.setText("");
        composeTagClass.setText("");
    } // end of method clearComposeScreen
    private void uploadFile (){

    }


} // end of class

/* Code for file browsing and image handling,
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



 */
