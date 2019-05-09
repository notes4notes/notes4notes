package com.example.notes4notes.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes4notes.Adapters.PostsAdapter;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AbstractFragment {

    private RecyclerView rvHome;

    /** Posts */
    protected List <Post> mPosts;

    static final String TAG = "Home Fragment";
    protected PostsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerViewConfig(view);
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
                    Log.d(TAG, "Post" + posts.get(i).getDescription() + " username:" + post.getUser().getUsername());
                }
            }
        });
    } // end of Query Posts

    protected void recyclerViewConfig(View view){
        rvHome = view.findViewById(R.id.recycleViewPosts);
        /* Create the data source */
        mPosts  = new ArrayList<>();

        /*  Create the Adapter */
        adapter = new PostsAdapter(getContext(), mPosts);

        /* Set the adapter on the layout manager.*/
        rvHome.setAdapter(adapter);

        /* set the layout manager on the recycler view.*/
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));

    } // recyclerViewConfig


}// end of class