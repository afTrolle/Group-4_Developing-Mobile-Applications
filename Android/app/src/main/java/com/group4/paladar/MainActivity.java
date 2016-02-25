package com.group4.paladar;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;
import com.group4.paladar.FireBaseLogic.FireBaseLoginHelper;
import com.group4.paladar.FireBaseLogic.FirebaseHelper;
import com.group4.paladar.Fragments.FragmentHandler;
import com.group4.paladar.Fragments.views.SearchFragment;
import com.group4.paladar.Fragments.views.SettingsFragment;
import com.group4.paladar.Navigation.ToolbarHelper;

public class MainActivity extends FirebaseLoginBaseActivity implements ToolbarHelper.onNavigationItemClicked {

    //Firebase Tools
    private FirebaseHelper firebaseHelper = new FirebaseHelper();
    private FireBaseLoginHelper fireBaseLoginHelper = new FireBaseLoginHelper();

    //Navigation tools
    private ToolbarHelper toolbarHelper = new ToolbarHelper();

    //Fragment Handler changes the content field
    //aka changes between different views
    private FragmentHandler fHandler = new FragmentHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseHelper.onCreate(this);
        toolbarHelper.onCreate(this, savedInstanceState);
        toolbarHelper.setOnNavigationItemListener(this);
        fHandler.onCreate(this);

        //make sure it's first time starting, avoid multiple layers of fragments
        if (savedInstanceState == null){

            fHandler.openFragment(new SearchFragment());
            Toast.makeText(this,"open Search",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        //sets up login providers (Facebook & Google)
        firebaseHelper.onStart(this);
    }


    /* -----------------------  Fire Base Handlers ------------------------------ */
    //events that are fired when login-in functions.
    @Override
    protected Firebase getFirebaseRef() {
        return firebaseHelper.getFirebase();
    }


    //means that login failed with some provider error (google or facebook)
    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        fireBaseLoginHelper.onFirebaseLoginProviderError(this, firebaseLoginError);
    }

    // user error user did something (canceled login?)
    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        fireBaseLoginHelper.onFirebaseLoginUserError(this, firebaseLoginError);
    }

    //called when login is was successful
    @Override
    protected void onFirebaseLoggedIn(AuthData authData) {
        super.onFirebaseLoggedIn(authData);
        fireBaseLoginHelper.onFirebaseLoggedIn(this, authData);

        //generate user profile header for navigation drawer
        toolbarHelper.SetUserProfile(fireBaseLoginHelper.getUserObject());
        //change sign in navigation item Text to sign out
        toolbarHelper.setSignOut();

    }

    //called when logout was successful
    @Override
    protected void onFirebaseLoggedOut() {
        super.onFirebaseLoggedOut();
        fireBaseLoginHelper.onFirebaseLoggedOut(this);

        //remove navigation drawer profile
        toolbarHelper.removeUserProfile();
        //Change navigation sign item to sign in
        toolbarHelper.setSignIn();
    }



    /*  -------------------------------- Navigation Item Clicks  ------------------------------------------------- */
        //called when navigation items are clicked!
    @Override
    public void onSearch() {

    }

    @Override
    public void onFilter() {

    }

    @Override
    public void onReservation() {

    }

    @Override
    public void onCreateHome() {

    }

    @Override
    public void onCreateEvent() {

    }

    @Override
    public void onSettings() {
        fHandler.changeViewWithBackStack(new SettingsFragment());
    }

    /**
     * Navigation sign in/out button onClicked!
     */
    @Override
    public void onSign() {
        if (fireBaseLoginHelper.isUserSignedIn()){
            //user is signed in, start signout process
            logout();
        } else {
            //user isn't signed in, start sign in process
            showFirebaseLoginPrompt();
        }
    }



  /*  -------------------------------- OTHER ------------------------------------------------- */

}
