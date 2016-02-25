package com.group4.paladar.FireBaseLogic;

import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;
import com.group4.paladar.FireBaseStructures.user.User;

/**
 * Created by Surface on 2016-02-23.
 */
public class FireBaseLoginHelper {


    // tells if user is signed in or not!
    private boolean isUserSignedIn = false;

    private User UserObject = null;


    public boolean isUserSignedIn() {
        return isUserSignedIn;
    }

    public User getUserObject() {
        return UserObject;
    }

    public void onFirebaseLoggedIn(FirebaseLoginBaseActivity mainActivity, AuthData authData) {
        Toast.makeText(mainActivity,"Login success",Toast.LENGTH_SHORT).show();
        //handle succesfull login
        UserObject = User.generateClient(authData);
        isUserSignedIn = true;
    }

    public void onFirebaseLoggedOut(FirebaseLoginBaseActivity mainActivity) {
        Toast.makeText(mainActivity,"Logout success",Toast.LENGTH_SHORT).show();
        //TODO handle succefull logout
        UserObject = null;
        isUserSignedIn = false;
    }

    public void onFirebaseLoginUserError(FirebaseLoginBaseActivity mainActivity, FirebaseLoginError firebaseLoginError) {
        Toast.makeText(mainActivity,"Login User Error",Toast.LENGTH_SHORT).show();
        //TODO handle failed user login

    }

    public void onFirebaseLoginProviderError(FirebaseLoginBaseActivity mainActivity, FirebaseLoginError firebaseLoginError) {
        Toast.makeText(mainActivity,"Login Provider Error",Toast.LENGTH_SHORT).show();
        // TODO handle failed provider login
    }


}
