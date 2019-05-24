package com.example.notes4notes.Models;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Comment")
public class Comment extends ParseObject {
    private static final  String KEY_COMMENT_USER         = "commentUser"   ;
    private static final  String KEY_COMMENT_POST         = "commentPost"   ;
    private static final  String KEY_COMMENT_CONTENT      = "commentContent";
    private static final  String KEY_COMMENT_CREATED_AT   = "createdAt"     ;
    private static final  String KEY_POST_ID              = "postID"        ;

    //Getter Methods
    public static String getKeyCommentUser() {
        return KEY_COMMENT_USER;
    }
    public void setKeyCommentUser(String user){put(KEY_COMMENT_USER, user);}

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
