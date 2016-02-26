package com.group4.paladar.Fragments.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import com.group4.paladar.R;

/**
 * Created by Surface on 2016-02-25.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);


        final CheckBoxPreference pref  = (CheckBoxPreference)getPreferenceScreen().findPreference("ChefStatus");
        if(pref.isChecked()){
            pref.setSelectable(false);
        }
        getPreferenceScreen().setOnPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        getPreferenceScreen().setOnPreferenceChangeListener(null);
        super.onDestroy();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        Toast.makeText(getActivity(), "item picked "+preference.getKey() , Toast.LENGTH_SHORT).show();

        //user  clicked on item that doesn't contain key
        if(preference.getKey() != null){

            if (preference.getKey().equals("ChefStatus")){
                //shows warning message that person is becoming a chef.
                 ShowBecomingChefWarinig(preference);
                return true;
            } else if (preference.getKey().equals("ChefImage")){

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Show only images, no videos or anything else
                intent.setType("image/*");
              //  intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }

        }


        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            //TODO Write Image To database!

            Toast.makeText(getActivity(),"Got Image",Toast.LENGTH_SHORT).show();

        }

    }


    //creates warning message that person is becoming a chef!
    private void ShowBecomingChefWarinig(final Preference preference){

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Warning");
        builder.setMessage(R.string.alert_chef_message);
        final CheckBoxPreference pref = (CheckBoxPreference)preference;


        if(pref.isChecked()) {
            //person isn't a chef

            builder.setPositiveButton(R.string.alert_okay, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    pref.setChecked(true);

                    pref.setSelectable(false);
                  //  pref.setEnabled(false);
                }
            })
                    .setNegativeButton(R.string.alert_cancel,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            pref.setChecked(false);
                            pref.setSelectable(true);
                        }
                    });

            // Create the AlertDialog object and return it
            AlertDialog lolz = builder.create();

            lolz.show();
        } else {
            pref.setSelectable(false);
        }


    }


    // calls for updating activity
    public interface onUpdateUserListener {
        public void onNewPreference(Preference preference, Object newValue);
    }
    onUpdateUserListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onUpdateUserListener) getActivity();
        } catch (ClassCastException e) {}

    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Toast.makeText(getActivity(),"new stuff",Toast.LENGTH_SHORT).show();
        mCallback.onNewPreference(preference,newValue);
        return false;
    }
}
