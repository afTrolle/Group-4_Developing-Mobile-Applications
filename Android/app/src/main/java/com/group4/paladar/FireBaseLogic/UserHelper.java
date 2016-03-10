package com.group4.paladar.FireBaseLogic;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.group4.paladar.FireBaseStructures.user.UserFirebase;
import com.group4.paladar.MainActivity;
import com.group4.paladar.Navigation.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surface on 2016-02-26.
 */
public class UserHelper {


    private boolean userSignedIn = false;

    private UserFirebase user;

    Firebase mRef;
    SharedPreferences sharedPref;

    public void onCreate(MainActivity mActivity){
        mRef = new Firebase("https://paladar-android.firebaseio.com/");
        sharedPref = PreferenceManager.getDefaultSharedPreferences(mActivity);
    }

    public boolean isUserSignedIn() {
        return userSignedIn;
    }

    public void onFirebaseLoggedIn(final MainActivity mActivity, final AuthData authData, final ToolbarHelper toolbarHelper) {
        userSignedIn = true;

        //Check if it is a first time sign in.
        mRef.child("users").child(authData.getUid()).addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // do some stuff once
                if (snapshot.exists()) {
                    //User Exists
                    Log.i("Signin", "User exists in database");
                    //LoadSettings!


                    //downloaded user settings from web!
                    user = snapshot.getValue(UserFirebase.class);

                } else {
                    //user Doesn't Exist
                    Log.i("Signin", "User doesn't exist in database");
                    user = addUserToDatabse(authData);
                }


                if(user.getChef().getIsChefEnabled()){
                    mActivity.toolbarHelper.enableChefOptions();
                }
                setPreferences(user, mActivity);

                //TODO UPDATE USER Navigation item here!
                //generate user profile header for navigation drawer
                toolbarHelper.SetUserProfile(user);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });


    }


    //add defualt stuff to user!
    private UserFirebase addUserToDatabse(AuthData authData){

        List<String> attendings = new ArrayList<String>();
        List<String> hostings = new ArrayList<String>();
        attendings.add("N/A");
        hostings.add("N/A");

        UserFirebase user = new UserFirebase(
                authData.getProvider(),
                authData.getProviderData().containsKey("displayName") ?  authData.getProviderData().get("displayName").toString() : "N/A",
                authData.getProviderData().containsKey("email") ?  authData.getProviderData().get("email").toString() : "N/A",
                authData.getProviderData().containsKey("profileImageURL") ?  authData.getProviderData().get("profileImageURL").toString() : "N/A",
                false,
                "N/A",
                "N/A",
                "N/A",
                attendings,
                hostings
        );

        mRef.child("users").child(authData.getUid()).setValue(user);
        return user;
    }



       private void setPreferences(UserFirebase user, MainActivity mActivity){

           SharedPreferences.Editor prefEditor = sharedPref.edit();
           //prefEditor.clear();

           prefEditor.putString("prefEmail",user.getEmail());

           prefEditor.putBoolean("ChefStatus",user.getChef().getIsChefEnabled());
           prefEditor.putString("ChefAlias", user.getChef().getAlias());
           prefEditor.putString("ChefSummary", user.getChef().getSummary());
           prefEditor.putString("ChefImage", user.getChef().getImage());

           prefEditor.commit();

       }


    public void onFirebaseLoggedOut(MainActivity mainActivity) {
        userSignedIn = false;
        mainActivity.toolbarHelper.disableChefOptions();
    }


    public void newPreferenceSet(SharedPreferences preference, String key, MainActivity mAcivity) {

        if (!key.equals(null)){

            try {

            if (key.equals("prefEmail")){
                user.setEmail((String) preference.getString(key,"N/A"));

            } else if (key.equals("ChefStatus"))
            {
                user.getChef().setIsChefEnabled(preference.getBoolean(key, false));
                if (user.getChef().getIsChefEnabled()){
                    mAcivity.toolbarHelper.enableChefOptions();
                } else {
                    mAcivity.toolbarHelper.disableChefOptions();
                }
            } else if (key.equals("ChefAlias"))
            {
                user.getChef().setAlias( preference.getString(key,"N/A"));
            } else if (key.equals("ChefSummary"))
            {
                user.getChef().setSummary( preference.getString(key,"N/A"));
            } else if (key.equals("ChefImage")) {
                user.getChef().setImage( preference.getString(key,"N/A"));
            }
                mRef.child("users").child(mAcivity.getAuth().getUid()).setValue(user);

            }catch (Exception e){
            }

        }
    }



    public static void getUserInformation(String uid, ValueEventListener listener ){

        Firebase ref = new Firebase("https://paladar-android.firebaseio.com/users").child(uid);
        ref.addListenerForSingleValueEvent(listener);
    }

    public static  void setUserInformation(String uid, UserFirebase user) {

        Firebase ref = new Firebase("https://paladar-android.firebaseio.com/users").child(uid);
        ref.setValue(user);
    }

}
