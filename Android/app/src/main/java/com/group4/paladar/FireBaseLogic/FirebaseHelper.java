package com.group4.paladar.FireBaseLogic;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;

/**
 * Created by Surface on 2016-02-23.
 * helper class for handling firebas calls in mainActivity.
 */
public class FirebaseHelper {

    private Firebase mRef; //Firebase root route.

    public Firebase getFirebase(){
        return mRef;
    }

    public void onCreate(FirebaseLoginBaseActivity mainActivity){

        //sets up Firebase connection
        Firebase.setAndroidContext(mainActivity);
        mRef = new Firebase("https://paladar-android.firebaseio.com/");
    }

    public void onStart(FirebaseLoginBaseActivity mainActivity){

        //enable different providers of login.
        //mainActivity.setEnabledAuthProvider(AuthProviderType.PASSWORD); //Disabled for now.
        mainActivity.setEnabledAuthProvider(AuthProviderType.FACEBOOK);
        mainActivity.setEnabledAuthProvider(AuthProviderType.GOOGLE);
    }

}


