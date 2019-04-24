package com.example.notes4notes;

import android.app.Application;

import com.example.notes4notes.Models.Post;
import com.parse.Parse;
import com.parse.ParseObject;


public class ParseApplication extends Application{
    private static final String appId     = "notes-4-notes"                                        ;
    private static final String clientKey = "sharingIsCaringPleaseShareAllOfYourNotesOnOurPlatform";
    private static final String serverURL = "http://notes-4-notes.herokuapp.com/parse"             ;
    @Override
    public void onCreate() {
        super.onCreate();
        //TO-DO ,
        ParseObject.registerSubclass(Post.class);
            /*
            *set applicationId, and server server based on the values in the Heroku settings.
            *clientKey is not needed unless explicitly configured
            * any network interceptors must be added with the Configuration Builder given this syntax
            */
            Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(appId) // should correspond to APP_ID env variable
                .clientKey(clientKey)  // set explicitly unless clientKey is explicitly configured on Parse server
                .server(serverURL).build());
    }
}

