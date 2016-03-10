package com.group4.paladar.Utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import com.group4.paladar.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Surface on 2016-03-07.
 */
public class ImageHandler {

    public static final int RESULT_LOAD_IMAGE = 1337;

    private static File file;

    public interface onImageGatheredCallback {
        void onImageRecieved(Bitmap bmp);
    }

    private static onImageGatheredCallback cback;

   public static void getImage(onImageGatheredCallback callback ,MainActivity mActivity){

       //setup temp file
       file = new File(mActivity.getExternalFilesDir(null), "paladar.png");

       cback = callback;
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageIntent.setType("image/*");
        pickImageIntent.putExtra("crop", "true");
        pickImageIntent.putExtra("aspectX", 16);
        pickImageIntent.putExtra("aspectY", 9);
       pickImageIntent.putExtra("outputX",480);
       pickImageIntent.putExtra("outputY",270);
       pickImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
          pickImageIntent.putExtra("scale", true);
      // pickImageIntent.putExtra("return-data", true);
       pickImageIntent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());

        mActivity.startActivityForResult(pickImageIntent, RESULT_LOAD_IMAGE);
    }


    public static String getImageAsString(Bitmap bmp){
        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
       // bmp.recycle();
        byte[] byteArray = bYtE.toByteArray();
        try {
            bYtE.close();
        }catch (Exception e){}

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Bitmap getImageasBitmap(String encodedImage){
        byte[] byteArray = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }


    public static void Imagerecieved(Intent data, MainActivity mActivitiy){

        Bitmap bmp = null;
        Uri selectedImageUri = data.getData();
        if (selectedImageUri == null){
            Toast.makeText(mActivitiy,"Falied to get image2!",Toast.LENGTH_SHORT).show();

        }
        try {
           // bmp = MediaStore.Images.Media.getBitmap(mActivitiy.getContentResolver(), Uri.fromFile(file));

            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

            bmp = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions);


        } catch (Exception e){
            e.printStackTrace();

        }

        cback.onImageRecieved(bmp);
    }

}
