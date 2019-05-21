package com.example.notes4notes.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("UploadedFiles")
public class UploadedFiles extends ParseObject {

    private static final String KEY_UPLOADED_FILES_UPLOADER         = "fileUploader"    ;
    private static final String KEY_UPLOADED_FILES_ASSOCIATED_POST  = "associatedPost"  ;
    private static final String KEY_UPLOADED_FILES                  = "file"            ;

    //Set Methods

    public void linkPost(String postID){
        this.put(getKeyAssociatedPost(), postID);
    }
    public void linkUploader(String uploader){
        this.put(getKeyUploader(), uploader);
    }
    public void packageFiles(){
    }

    // Getter Methods
    public String getFileUploader(){
        return this.getString(getKeyUploader());
    }

    public String getAssociatedPost(){
        return this.getString(getKeyAssociatedPost());
    }
    public String getAssociatedFiles(){
        return "";
    }

    public static String getKeyUploader() {
        return KEY_UPLOADED_FILES_UPLOADER;
    }

    public static String getKeyAssociatedPost() {
        return KEY_UPLOADED_FILES_ASSOCIATED_POST;
    }

    public static String getKeyUploadedFiles() {
        return KEY_UPLOADED_FILES;
    }


}// end of class Uploaded Files
