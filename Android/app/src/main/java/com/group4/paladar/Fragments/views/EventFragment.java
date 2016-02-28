package com.group4.paladar.Fragments.views;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.group4.paladar.R;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Surface on 2016-02-26.
 */
public class EventFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_event,container,false);

        Button event_imageButton = (Button)root.findViewById(R.id.event_image_button);
        event_imageButton.setOnClickListener(this);


        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



    }


    final int PICK_FROM_CAMERA = 1337;
    final int CROP_FROM_CAMERA = 1338;
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");


        try {
            startActivityForResult(intent, PICK_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {
            //Do nothing for now
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch(requestCode) {
            case PICK_FROM_CAMERA:
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = data.getData();
                    try {

                        Intent intent = new Intent("com.android.camera.action.CROP");
                        intent.setClassName("com.android.camera", "com.android.camera.CropImage");

                        intent.setData(selectedImage);
                        intent.putExtra("aspectX", 16);
                        intent.putExtra("aspectY", 9);
                        intent.putExtra("scale", true);
                        intent.putExtra("return-data", true);
                        startActivityForResult(intent, CROP_FROM_CAMERA);

                    } catch (Exception e){

                    }
                }
        }
    }
}
