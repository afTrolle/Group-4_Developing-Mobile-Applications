package com.group4.paladar.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;

import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.group4.paladar.Fragments.views.SettingsFragment;
import com.group4.paladar.R;

/**
 * Handles moving between views!
 */
public class FragmentHandler {

    FragmentManager fManager;

    public void onCreate(FirebaseLoginBaseActivity mActivity) {
        fManager=  mActivity.getFragmentManager();
    }

    /**
     * Used to change view when you want it as a root for navigation.
     * should for example be called when search is clicked on nav items.
     * @param frag
     */
    public void ChangeView(Fragment frag){

        //should empty backstack.
        fManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //set this fragment as root fragment
        fManager.beginTransaction().replace(R.id.content_frame, frag).commit();
    }

    /**
     * means that if you hit return it will return to the previous fragment
     * so settings page could use this or something else.
     * @param frag
     */
    public void  changeViewWithBackStack(Fragment frag){
        fManager.beginTransaction().replace(R.id.content_frame, frag).addToBackStack(null).commit();
    }

   public void openFragment(Fragment frag){
       fManager.beginTransaction().add(R.id.content_frame, frag).commit();
   }


    public  boolean popStack(){

        if (fManager.getBackStackEntryCount() > 0) {
            fManager.popBackStack();
            return true;
        } else {
            return false;
        }

    }
}
