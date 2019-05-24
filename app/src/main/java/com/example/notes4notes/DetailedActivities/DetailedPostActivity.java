package com.example.notes4notes.DetailedActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.notes4notes.Adapters.CommentAdapter;
import com.example.notes4notes.Adapters.PostsAdapter;
import com.example.notes4notes.MainActivity;
import com.example.notes4notes.Models.Comment;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DetailedPostActivity extends AppCompatActivity {

    Button backButton;
    ImageView detailPostImageView;
    TextView detailPostTitle;
    TextView detailPostAuthor;
    TextView detailPostDescription;
    RatingBar detailPostRating;

    RecyclerView rvComments;
    List<Comment> comments;
    CommentAdapter adapter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_post_detail);
        backButton = findViewById(R.id.detailBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        detailPostImageView = findViewById(R.id.postDetailAuthorImage);
        detailPostTitle = findViewById(R.id.postDetailTitle);
        detailPostAuthor = findViewById(R.id.postDetailAuthor);
        detailPostDescription = findViewById(R.id.postDetailDescription);
        detailPostRating = findViewById(R.id.postDetailRating);
        rvComments = findViewById(R.id.postDetailCommentStream);

        String postAuthor = getIntent().getStringExtra("postAuthor");
        String postTitle = getIntent().getStringExtra("postTitle");
        String postDescription = getIntent().getStringExtra("postDescription");
        String postImg = getIntent().getStringExtra("postImg");
        String postString = getIntent().getStringExtra("postString");
        int rating = Integer.parseInt(getIntent().getStringExtra("postRating"));

        detailPostTitle.setText(postTitle);
        detailPostAuthor.setText(postAuthor);
        detailPostDescription.setText(postDescription);
        Glide.with(this).load(postImg).apply(new RequestOptions().override(0, 0))
                .apply(new RequestOptions().fitCenter())
                .into(detailPostImageView);
        detailPostRating.setRating(rating);

        comments  = new ArrayList<>();

        /*  Create the Adapter */
        adapter = new CommentAdapter(this, comments);

        /* Set the adapter on the layout manager.*/
        rvComments.setAdapter(adapter);

        /* set the layout manager on the recycler view.*/
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();

    }

    protected void queryPosts(){
        adapter.clear();

        ParseQuery<Comment> commentQuery = new ParseQuery<Comment>(Comment.class);
        //commentQuery.include("commentContent");
        commentQuery.include(Comment.KEY_OBJECT_ID);
        commentQuery.setLimit(3);
        commentQuery.addDescendingOrder(Comment.KEY_CREATED_AT);
        commentQuery.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> commentsa, ParseException e) {
                if (e != null){
                    Log.e("test", "Error with query");
                    e.printStackTrace();
                    return;
                }
                comments.addAll(commentsa);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < commentsa.size(); i++){
                    Comment comment = (Comment)commentsa.get(i);

                }
            }
        });
    } // end of Query Posts
}

