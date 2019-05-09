package com.example.notes4notes.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.Models.User;
import com.example.notes4notes.R;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    private Context context;
    private List<Post> posts;

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

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            postAuthorImage = itemView.findViewById (R.id.postAuthorImage);
            postTitle = itemView.findViewById(R.id.postTitle);
            postAuthor = itemView.findViewById(R.id.postAuthor);
            postDescription = itemView.findViewById(R.id.postDescription);
            postRating = itemView.findViewById(R.id.postRating);
            postLikeButton = itemView.findViewById(R.id.postLikeButton);
            postUnlikeButton = itemView.findViewById(R.id.postUnlikeButton);
            postCommentButton = itemView.findViewById(R.id.postCommentButton);
            postDownloadButton = itemView.findViewById(R.id.postDownloadButton);
        }

         void bind(Post post){
            //TODO: bid the view elements to the post
            Log.e("POSTS_adapter", "bind method invoked");
            postTitle.setText(post.getUser().getString(User.getKeyUserUsername()));
            ParseFile profilePic = post.getUser().getParseFile(User.getKeyUserProfilePic());
            Glide.with(context).load(profilePic).into(postAuthorImage);
        }


    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

}