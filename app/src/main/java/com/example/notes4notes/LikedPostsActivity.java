package com.example.notes4notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes4notes.Activities.CommentActivity;
import com.example.notes4notes.Adapters.PostsAdapter;
import com.example.notes4notes.Models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class LikedPostsActivity extends AppCompatActivity {

    private RecyclerView rvLiked;
    protected List<Post> mPosts;
    static final String TAG = "Liked Activity";
    protected PostsAdapter adapter;
    Button likedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);
        likedBtn = findViewById(R.id.likedButton);
        likedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        rvLiked = findViewById(R.id.likedrecycleViewPosts);
        /* Create the data source */
        mPosts  = new ArrayList<>();

        /*  Create the Adapter */
        adapter = new PostsAdapter(this, mPosts);

        /* Set the adapter on the layout manager.*/
        rvLiked.setAdapter(adapter);

        /* set the layout manager on the recycler view.*/
        rvLiked.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
    }

    protected void queryPosts(){
        //adapter.clear();

        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.getKeyPostUser());
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < posts.size(); i++){
                    Post post = posts.get(i);

                }
            }
        });
    } // end of Query Posts
}

