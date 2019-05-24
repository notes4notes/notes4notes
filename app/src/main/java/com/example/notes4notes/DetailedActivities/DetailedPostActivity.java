package com.example.notes4notes.DetailedActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.notes4notes.MainActivity;
import com.example.notes4notes.R;

public class DetailedPostActivity extends AppCompatActivity {

    Button backButton;
    ImageView detailPostImageView;
    TextView detailPostTitle;
    TextView detailPostAuthor;
    TextView detailPostDescription;
    RatingBar detailPostRating;
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

    }
}

