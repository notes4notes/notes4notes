package com.example.notes4notes;

import android.content.Context;
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
import com.parse.Parse;
import com.parse.ParseFile;

import org.w3c.dom.Text;

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

        public ViewHolder(@NonNull View itemView) {
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

        public void bind(Post post){
            //TODO: bid the view elements to the post
            postTitle.setText(post.getUser().getUsername());
            ParseFile profilePic = post.getUser.getProfilePic();
            Glide.with(context).load();

        }
    }
}
