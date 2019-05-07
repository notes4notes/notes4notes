package com.example.notes4notes.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {

    private static final  String KEY_USER_ID           = "objectId"    ;
    private static final  String KEY_USER_FIRST_NAME   = "firstName"   ;
    private static final  String KEY_USER_LAST_NAME    = "lastName"    ;
    private static final  String KEY_USER_USERNAME     = "username"    ;
    private static final  String KEY_USER_CREATED_AT   = "createdAt"   ;
    private static final  String KEY_USER_EMAIL        = "email"       ;
    private static final  String KEY_USER_PROFILE_PIC  = "userProfilePic" ;



    public static String getKeyUserProfilePic() {
        return KEY_USER_PROFILE_PIC;
    }



    /*Getter Methods */
    public static String getKeyUserId() {
        return KEY_USER_ID;
    }

    public static String getKeyUserFirstName() {
        return KEY_USER_FIRST_NAME;
    }

    public static String getKeyUserLastName() {
        return KEY_USER_LAST_NAME;
    }

    public static String getKeyUserUsername() {
        return KEY_USER_USERNAME;
    }

    public static String getKeyUserCreatedAt() {
        return KEY_USER_CREATED_AT;
    }

    public static String getKeyUserEmail() {
        return KEY_USER_EMAIL;
    }
} // end of class
    