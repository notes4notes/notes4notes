package com.example.notes4notes.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.notes4notes.Adapters.PostsAdapter;
import com.example.notes4notes.LikedPostsActivity;
import com.example.notes4notes.LoginActivity;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.Models.User;
import com.example.notes4notes.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends AbstractFragment {

    private TextView profileName;
    private TextView profileUsername;
    private ImageButton profileLikedPosts;
    private ImageButton profileLogout;
    private ImageView profileProfilePic;
    private RecyclerView rvProfile;

        /** Posts */
        private List <Post> mPosts;

        static final String TAG = "Profile Fragment";
        private PostsAdapter adapter;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_profile, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            profileBind(view);
            recyclerViewConfig(view);
            queryPosts();
        }


        protected void recyclerViewConfig(View view){
            rvProfile = view.findViewById(R.id.rv_profile);
            /* Create the data source */
            mPosts  = new ArrayList<>();

            /*  Create the Adapter */
            adapter = new PostsAdapter(getContext(), mPosts);

            /* Set the adapter on the layout manager.*/
            rvProfile.setAdapter(adapter);

            /* set the layout manager on the recycler view.*/
            rvProfile.setLayoutManager(new LinearLayoutManager(getContext()));

        } // recyclerViewConfig


        protected void queryPosts(){
        //adapter.clear();
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.getKeyPostUser());
        postQuery.whereEqualTo(Post.getKeyPostUser(), ParseUser.getCurrentUser());
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
                    Log.d(TAG, "Post" + posts.get(i).getDescription() + " username:" + post.getPostAuthor().getUsername());
                }
            }
        });
    } // end of Query Posts

    protected void profileBind(View view){
        profileName = view.findViewById(R.id.profile_name);
        profileUsername = view.findViewById(R.id.profile_username);
        profileLikedPosts = view.findViewById(R.id.profile_liked_posts);
        profileLogout = view.findViewById(R.id.profile_logout);
        profileProfilePic = view.findViewById(R.id.profile_profile_pic);
        String fullName = ParseUser.getCurrentUser().getString(User.getKeyUserFirstName())
                + " " + ParseUser.getCurrentUser().getString(User.getKeyUserLastName());
        profileName.setText(fullName);
        profileUsername.setText(ParseUser.getCurrentUser().getUsername());
        ParseFile profilePic = ParseUser.getCurrentUser().getParseFile(User.getKeyUserProfilePic());
        if (profilePic !=null)
            Glide.with(view.getContext()).load(profilePic.getUrl()).override(300,300).into(profileProfilePic);
        else
            Glide.with(view.getContext()).load("@drawable/ic_profile").apply(new RequestOptions().centerCrop()).into(profileProfilePic);

        profileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Context context = ProfileFragment.this.getActivity();
                Intent i = new Intent(context, LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }

        });

        profileLikedPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = ProfileFragment.this.getActivity();
                Intent i = new Intent(context, LikedPostsActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
    }

}
