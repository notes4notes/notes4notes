package com.example.notes4notes.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Comment")
public class Comment extends ParseObject {
    private static final  String KEY_COMMENT_USER         = "commentUser"   ;
    private static final  String KEY_COMMENT_POST         = "commentPost"   ;
    private static final  String KEY_COMMENT_CONTENT      = "commentContent";
    private static final  String KEY_COMMENT_CREATED_AT   = "createdAt"     ;

    //Getter Methods
    public static String getKeyCommentUser() {
        return KEY_COMMENT_USER;
    }

    public static String getKeyCommentPost() {
        return KEY_COMMENT_POST;
    }

    public static String getKeyCommentContent() {
        return KEY_COMMENT_CONTENT;
    }

    public static String getKeyCommentCreatedAt() {
        return KEY_COMMENT_CREATED_AT;
    }
} // End of class
