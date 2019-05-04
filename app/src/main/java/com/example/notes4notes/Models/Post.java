package com.example.notes4notes.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Post")
public class Post extends ParseObject {

    private static final  String KEY_POST_TITLE        = "title"       ;
    private static final  String KEY_POST_DESCRIPTION  = "description" ;
    private static final  String KEY_POST_CREATED_AT   = "createdAt"   ;
    private static final  String KEY_POST_RATING       = "postRating"  ;
    private static final  String KEY_POST_FILE         = "postFile"    ;
    /* Getter Methods */

    public static String getKeyPostTitle() {
        return KEY_POST_TITLE;
    }

    public static String getKeyPostDescription() {
        return KEY_POST_DESCRIPTION;
    }

    public static String getKeyPostCreatedAt() {
        return KEY_POST_CREATED_AT;
    }

    public static String getKeyPostRating() {
        return KEY_POST_RATING;
    }

    public static String getKeyPostFile() {
        return KEY_POST_FILE;
    }
} // end of Class
