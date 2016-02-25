package com.group4.paladar.FireBaseStructures.user;

import com.firebase.client.AuthData;

/**
 * Created by Surface on 2016-02-23.
 */
public class User {

    private String Uid; //A unique user ID, intended as unique key across all providers.
    private String provider; // Either Google or FaceBook
    private String displayName; //name of the user
    private String faceBookUserID; //Facebook user id finding facebook page!
    private String googleUserID; //googleUserID
    private String email;   //user email

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUid() {
        return Uid;
    }

    private String profileImageURL; //user image url

    public static User generateClient(AuthData authData) {
        User client =  new User();

        client.Uid = authData.getUid();
        client.provider = authData.getProvider();
        client.displayName = (String) authData.getProviderData().get("displayName");
        if ( client.provider == "google"){
            client.googleUserID =  (String)  authData.getProviderData().get("id");
        } else if ( client.provider == "facebook"){
            client.faceBookUserID =  (String)  authData.getProviderData().get("id");
        }
        client.email = (String) authData.getProviderData().get("email");
        client.profileImageURL = (String) authData.getProviderData().get("profileImageURL");

        return client;
    }
}
