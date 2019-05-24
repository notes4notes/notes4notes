package com.example.notes4notes.Models;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze = Post.class)
@ParseClassName("Post")
public class Post extends ParseObject implements Parcelable {

    private static final  String KEY_POST_TITLE        = "postTitle"       ;
    private static final  String KEY_POST_DESCRIPTION  = "postDescription" ;
    private static final  String KEY_POST_RATING       = "postRating"      ;
    private static final  String KEY_POST_FILE         = "postFile"        ;
    private static final  String KEY_POST_CLASS        = "postClass"       ;
    private static final  String KEY_POST_USER         = "postUser"        ;
    private static final  String KEY_ID                = "objectId"        ;

    public Post(){ } // Required by Parceler

    /* Getter Methods for Fields */
    public  String  getPostUserName(){
        return  "@" + this.getPostAuthor().getString(User.getKeyUserUsername());
    }
    public ParseUser getPostAuthor(){
        return getParseUser(KEY_POST_USER);
    }
    public String getPostTitle() {
        return this.getString(getKeyPostTitle());
    }
    public String getPostDescription() {
        return this.getString(getKeyPostDescription());
    }
    public int getPostRating(){
        return this.getInt(getKeyPostRating());
    }
    public String getKeyId(){return this.getObjectId();}

    public String getPostAuthorProfileImageURL() {
        return this.getPostAuthor().getParseFile(User.getKeyUserProfilePic()).getUrl();
    }
    /* Getter Methods for Post Keys */


    public static String getKeyPostTitle() { return KEY_POST_TITLE; }

    public static String getKeyPostDescription() {return KEY_POST_DESCRIPTION;}

    public static String getKeyPostRating() {return KEY_POST_RATING; }

    public static String getKeyPostClass() {return KEY_POST_CLASS; }

    public static String getKeyPostUser() { return KEY_POST_USER; }

    public String getDescription(){
        return getString(KEY_POST_DESCRIPTION);
    }

    public void setKeyPostDescription(String description){
        put(KEY_POST_DESCRIPTION, description);
    }

    public String getRating(){
        return getString(KEY_POST_RATING);
    }

    public void setKeyPostRating(String rating){
        put(KEY_POST_RATING, rating);
    }

    public ParseFile getFile(){
        return getParseFile(KEY_POST_FILE);
    }

    public void setFile(ParseFile parseFile){
        put(KEY_POST_FILE, parseFile);
    }


    public void setUser (ParseUser parseUser){
        put(KEY_POST_USER, parseUser);
    }
    public String getTitle(){
        return getString(KEY_POST_TITLE);
    }

    public void setTitle(String title){
        put(KEY_POST_TITLE, title);

    }


} // end of Class
