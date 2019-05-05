package com.example.notes4notes.Models;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {

    private static final  String KEY_POST_TITLE        = "title"       ;
    private static final  String KEY_POST_DESCRIPTION  = "description" ;
    private static final  String KEY_POST_RATING       = "postRating"  ;
    private static final  String KEY_POST_FILE         = "postFile"    ;
    private static final  String KEY_POST_CLASS        = "postClass";
    private static final  String KEY_POST_USER        = "postUser";


    /* Getter & Setter Methods */

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
    public ParseUser getUser(){
        return getParseUser(KEY_POST_USER);
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
