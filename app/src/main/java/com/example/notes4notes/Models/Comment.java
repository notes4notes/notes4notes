package com.example.notes4notes.Models;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.parceler.Parcel;

@Parcel(analyze = Comment.class)
@ParseClassName("Comment")
public class Comment extends ParseObject implements Parcelable {
    private static final  String KEY_COMMENT_USER         = "commentUser"   ;
    private static final  String KEY_COMMENT_POST         = "commentPost"   ;
    private static final  String KEY_COMMENT_CONTENT      = "commentContent";
    private static final  String KEY_COMMENT_CREATED_AT   = "createdAt"     ;
    private static final  String KEY_POST_ID              = "postID"        ;
    private static final String username = "username";
    private static final String commentcontent = "commentContent";

    public Comment(){ }
    //Getter Methods
    public static String getKeyCommentUser() {
        return KEY_COMMENT_USER;
    }
    public void setKeyCommentUser(String user){put(KEY_COMMENT_USER, user);}
    public String getUsername(){ return getString(username);}
    public String getComment(){return getString(commentcontent);}
    public String getPostKey(){return getString("commentPost");}
    public static String getKeyCommentPost() {
        return KEY_COMMENT_POST;
    }
    public void setKeyCommentPost(String post){put(KEY_COMMENT_POST, post);}
    public static String getKeyCommentContent() {
        return KEY_COMMENT_CONTENT;
    }
    public void settKeyCommentContent(String com) {
        put(KEY_COMMENT_CONTENT, com);
    }
    public static String getKeyCommentCreatedAt() {
        return KEY_COMMENT_CREATED_AT;
    }
    public void setPostID(String postID){put(KEY_POST_ID, postID);}
} // End of class
