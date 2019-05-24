package com.example.notes4notes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.notes4notes.Activities.CommentActivity;
import com.example.notes4notes.DetailedActivities.DetailedPostActivity;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.R;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private static final String TAG = "Posts Adapter";
    private Context context;
    private List<Post> posts;
    private static final String placeHolderImage = "@drawable/ic_profile";

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView postAuthorImage;
        private TextView postTitle;
        private TextView postAuthor;
        private TextView postDescription;
        private RatingBar postRating;
        private ImageButton postLikeButton;
        private ImageButton postUnlikeButton;
        private Button postCommentButton;
        private Button postDownloadButton;
        private String postID;
        RelativeLayout container;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            postAuthorImage     = itemView.findViewById (R.id.postAuthorImage);
            postTitle           = itemView.findViewById(R.id.postTitle);
            postAuthor          = itemView.findViewById(R.id.postAuthor);
            postDescription     = itemView.findViewById(R.id.postDescription);
            postRating          = itemView.findViewById(R.id.postRating);
            postLikeButton      = itemView.findViewById(R.id.postLikeButton);
            postUnlikeButton    = itemView.findViewById(R.id.postUnlikeButton);
            postCommentButton   = itemView.findViewById(R.id.postCommentButton);
            postDownloadButton  = itemView.findViewById(R.id.postDownloadButton);
            container = itemView.findViewById(R.id.container);
        } // end of ViewHolder Constructor

         void bind(final Post post){

            Log.e("POSTS_adapter", "bind method invoked");
            postAuthor.setText(post.getPostUserName());
            postTitle.setText(post.getPostTitle());
            postDescription.setText(post.getPostDescription());
            postRating.setRating(post.getPostRating());
            String imgURL;
             try{
                 imgURL = post.getPostAuthorProfileImageURL();
                 Glide.with(context)
                         .load(imgURL)
                         .apply(new RequestOptions().override(0, 0))
                         .apply(new RequestOptions().fitCenter())
                         .into(postAuthorImage);
             }
             catch(Exception e){
                 imgURL = "http://www.protexinvet.com/userfiles/image/cute-2500929_1920_(1).jpg";
                 Glide.with(context)
                         .load("http://www.protexinvet.com/userfiles/image/cute-2500929_1920_(1).jpg")
                         .apply(new RequestOptions().override(0, 0))
                         .apply(new RequestOptions().fitCenter())
                         .into(postAuthorImage);
             }
             final String finalImgURL = imgURL;
             postTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailedPostActivity.class);
                    intent.putExtra("postAuthor", postAuthor.getText().toString());
                    intent.putExtra("postTitle", postTitle.getText().toString());
                    intent.putExtra("postDescription", postDescription.getText().toString());
                    intent.putExtra("postRating", ((int)postRating.getRating())+"");
                    intent.putExtra("postImg", finalImgURL);
                    intent.putExtra("postString", post.getObjectId());
                    v.getContext().startActivity(intent);
                }
            });
            postCommentButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CommentActivity.class);
                    intent.putExtra("postString", post.getObjectId());
                    intent.putExtra("postID", Parcels.wrap(post));
                    intent.putExtra("username", ParseUser.getCurrentUser().getUsername());
                    v.getContext().startActivity(intent);
                }
            });
             postDownloadButton.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View v) {

                 }
             });
        } // end of method bind
    } // end of View Holder Class.

    private void clear() {
        posts.clear();
        notifyDataSetChanged();
    } // end of addAll method

    // Add a list of items -- change to type used

    private void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    } // end of addAll method
/*
    private void startCommentActivity() {
        Intent i = new Intent(getContext(), RegisterActivity.class);
        startActivity(i);
    }
  */
    private void startDownloadActivity(){

    }

} // end of class Post Adapters