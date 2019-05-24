package com.example.notes4notes.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notes4notes.Models.Comment;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class CommentActivity extends AppCompatActivity {
    EditText commentEt;
    TextView commentTv;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        commentTv = findViewById(R.id.commenttv);
        Button btn = findViewById(R.id.commentSubmitBtn);
        commentTv.setText("100");
        commentEt = (EditText) findViewById(R.id.commentBody);
        commentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int newone = 100 - (s.length());
                if(newone > 100){
                    commentTv.setTextColor(Color.RED);
                }
                else{
                    commentTv.setTextColor(Color.GRAY);
                }
                commentTv.setText(newone+ "");
            }

            public void afterTextChanged(Editable s) {

            }
        });
        final String value = getIntent().getExtras().getString("postID");
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(Integer.parseInt(commentTv.getText().toString()) < 100 && Integer.parseInt(commentTv.getText().toString()) > 0 ) {
                    Toast.makeText(v.getContext(), "Comment Added! ", Toast.LENGTH_SHORT).show();
                  //  saveComment(commentEt.getText().toString());
                    finish();
                }
                else{
                    Toast.makeText(v.getContext(), "Invalid comment, try again" + value, Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(final View v) {
                if(Integer.parseInt(commentTv.getText().toString()) < 100 && Integer.parseInt(commentTv.getText().toString()) > 0 ) {
                    Toast.makeText(v.getContext(), "Comment Added! ", Toast.LENGTH_SHORT).show();
                    saveComment(commentEt.getText().toString());
                    finish();
                }
                else{
                    Toast.makeText(v.getContext(), "Invalid comment, try again" + value, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveComment(String commentText){
        Comment comment = new Comment();
                    /*comment.put("commentContent", commentEt.getText().toString());
                    comment.put("commentUser", ParseUser.getCurrentUser());
                    comment.put("postID", getIntent().getExtras().getString("postID"));*/
        /*comment.setPostID(getIntent().getExtras().getString("postID"));
        comment.setKeyCommentUser(ParseUser.getCurrentUser().toString());
        comment.settKeyCommentContent(commentText);*/
        comment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.d("comment", "savecomment error");
                    return;
                }
                Log.d("comment", "Success");
            }
        });
    }
}
