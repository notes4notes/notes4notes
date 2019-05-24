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
import com.example.notes4notes.Models.Comment;
import com.example.notes4notes.Models.Post;
import com.example.notes4notes.R;

import org.parceler.Parcels;

import java.util.List;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private static final String TAG = "Comments Adapter";
    private Context context;
    private List<Comment> commentsList;

    public CommentAdapter(Context context, List<Comment> comments){
        this.context = context;
        this.commentsList = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = commentsList.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView postAuthor;
        private TextView postDescription;
        private String postID;
        RelativeLayout container;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            postAuthor          = itemView.findViewById(R.id.commentAuthor);
            postDescription     = itemView.findViewById(R.id.commentText);
            container = itemView.findViewById(R.id.container);
        } // end of ViewHolder Constructor

        void bind( Comment comment){
            postAuthor.setText(comment.getUsername());
            postDescription.setText(comment.getComment());
 ;
        } // end of method bind
    }//end of viewholder class

    public void clear(){
        commentsList.clear();
        notifyDataSetChanged();
    }

    private void addAll(List<Comment> comments){
        commentsList.addAll(comments);
        notifyDataSetChanged();
    }
}
