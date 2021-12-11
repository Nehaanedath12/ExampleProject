package com.example.exampleproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;

import com.example.exampleproject.Sign.SignMainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.fotoapparat.result.PhotoResult;

public class Tools {
    public static void storeImage(Bitmap image, SignMainActivity signMainActivity) {

        File pictureFile = getOutputMediaFile(signMainActivity);
        if (pictureFile == null) {
            Log.d("TAGTAG", "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("TAGTAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAGTAG", "Error accessing file: " + e.getMessage());
        }
    }


    private static File getOutputMediaFile(SignMainActivity signMainActivity){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + signMainActivity.getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public static File savebitmap(Bitmap bmp, Context context) {
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        OutputStream outStream = null;
//        String filePath = context.getFilesDir().getPath().toString() + "/fileName.txt";
//        File f = new File(filePath);
        File file = new File(extStorageDirectory,"bmp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, bmp + ".png");
            Log.d("fileexist", "" + file + ",Bitmap= " + bmp);
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
            Log.d("filefile", "" + file);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("exceptionn", "" + e.toString());
        }

        return file;

    }
    public static String savePhoto(Context context, Bitmap image) {
        String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";
        File folder = new File(context.getExternalFilesDir(null) + File.separator + "temp");

        if (!folder.exists()) {
            Log.d("folder created:", "" + folder.mkdir());
        }
        File file = new File(folder, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("TAGTAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAGTAG", "Error accessing file: " + e.getMessage());
        }

//        photoResult.saveToFile(file);
        return file.getAbsolutePath();
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
        byte[] b = ByteStream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public int GetPixels(int dp, Context context) {
        float px = dp;
        try {
            Resources r = context.getResources();
            px = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp,
                    r.getDisplayMetrics()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) px;
    }

}
