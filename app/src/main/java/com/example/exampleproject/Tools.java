package com.example.exampleproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;
import java.io.File;

import io.fotoapparat.result.PhotoResult;

public class Tools {


    public static String savePhoto(Context context, PhotoResult photoResult) {
        String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";
        File folder = new File(context.getExternalFilesDir(null) + File.separator + "temp");

        if (!folder.exists()) {
            Log.d("folder created:", "" + folder.mkdir());
        }
        File file = new File(folder, fileName);
        photoResult.saveToFile(file);
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
        if (info != null && info.isConnectedOrConnecting()) {
            return true;
        } else
            return false;
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
