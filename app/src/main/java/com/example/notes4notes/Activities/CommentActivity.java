package com.example.notes4notes.Activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notes4notes.Models.Comment;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CommentActivity extends AppCompatActivity  {


    private static final String TAG = "Comment";
    EditText commentEt;
    TextView commentTv;
    Button btn;
    String textToSet = "100";
    ParseObject post;

    private void bind(){
        commentTv = findViewById(R.id.commenttv);
        btn = findViewById(R.id.commentSubmitBtn);
        commentTv.setText(textToSet);
        commentEt = findViewById(R.id.commentBody);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        bind();

        commentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int newone = 100 - (s.length());
                if(newone > 100){
                    commentTv.setTextColor(Color.RED);
                }
                else{
                    commentTv.setTextColor(Color.GRAY);
                }
                commentTv.setText(newone+  "");
            }
            public void afterTextChanged(Editable s) {

            }
        });



        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(final View v) {
                if(Integer.parseInt(commentTv.getText().toString()) < 100 && Integer.parseInt(commentTv.getText().toString()) > 0 ) {

                    String commentContent = commentEt.getText().toString();
                    saveComment(commentContent);
                    Toast.makeText(v.getContext(), "Comment Added! ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    String value ="idk";
                    Toast.makeText(v.getContext(), "Invalid comment, try again" + value, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveComment(String commentText){
        String postID = getIntent().getStringExtra("postString");
        ParseObject post = new Post();  // Post(postID);
        post.setObjectId(postID);
        ParseObject comment = ParseObject.create("Comment");
        try {
            comment.put(Comment.getKeyCommentContent(), commentText);
            comment.put(Comment.getKeyCommentUser(), ParseUser.getCurrentUser());
            comment.put(Comment.getKeyCommentPost(), post);
        }catch (Exception e){e.printStackTrace();}

        comment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.d("comment", "Error: Comment not Saved.");
                    return; }
                Log.d("comment", "Success: Comment Saved Successfully");
            }
        });
    } // end of method saveComment

}// end of class

