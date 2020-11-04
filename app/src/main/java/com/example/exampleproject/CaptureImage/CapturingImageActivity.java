package com.example.exampleproject.CaptureImage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exampleproject.DatabaseHelper;
import com.example.exampleproject.R;

import java.util.ArrayList;
import java.util.List;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.result.BitmapPhoto;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.result.WhenDoneListener;
import io.fotoapparat.view.CameraView;

public class CapturingImageActivity extends AppCompatActivity {
    RecyclerView captureRV;
    ImageCaptureAdapter captureAdapter;
    ImageView addImage, saveImage;
    List<ImageCaptureClass> list;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturing_imge);
        captureRV = findViewById(R.id.captureImageRecycle);
        addImage = findViewById(R.id.add_image);
        saveImage = findViewById(R.id.save_image);
        list = new ArrayList<>();
        helper = new DatabaseHelper(this);
        captureAdapter = new ImageCaptureAdapter(this, list);
        captureRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


//        loadImage();

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CapturingImageActivity.this, new String[]{Manifest.permission.CAMERA}, 100);
                } else {
                    addCaptureImage();
                    if (list.size() > 0) {
                        Log.d("sizeList", String.valueOf(list.size()));
                    }
                }
            }
        });


    }


    private void addCaptureImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.camera_layout, null, false);
        ImageView cancelImage, captureImage;
        CameraView cameraView = view.findViewById(R.id.camera_view);
        cancelImage = view.findViewById(R.id.cancel_image);
        captureImage = view.findViewById(R.id.capture_image);
        Fotoapparat fotoapparat = new Fotoapparat(this, cameraView);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(view);
        AlertDialog dialogue = builder.create();
        dialogue.show();
        fotoapparat.start();


        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue.dismiss();
                fotoapparat.stop();
            }
        });
        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoResult photoResult = fotoapparat.takePicture();
                photoResult.toBitmap().whenDone(new WhenDoneListener<BitmapPhoto>() {
                    @Override
                    public void whenDone(BitmapPhoto bitmapPhoto) {
                        if (bitmapPhoto != null) {
                            Log.d("captured Image", bitmapPhoto.bitmap.toString());
                            ImageCaptureClass captureClass = new ImageCaptureClass(bitmapPhoto);
                            list.add(captureClass);
//                            helper.insertImage(captureClass);
                            fotoapparat.stop();
                        }
                        captureRV.setAdapter(captureAdapter);
                        captureAdapter.notifyDataSetChanged();
                        dialogue.dismiss();
                    }
                });
            }
        });
    }

//    private void loadImage() {
//        Cursor cursor=helper.getImages();
//        if(cursor!=null && cursor.getCount()>0){
//
//            for(int i=0;i<cursor.getCount();i++){
//                list.add(new ImageCaptureClass(Tools.StringToBitMap((cursor.getString(cursor.getColumnIndex("image")))))) ;
//            }
//            captureRV.setAdapter(captureAdapter);
//            captureAdapter.notifyDataSetChanged();
//
//
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addCaptureImage();
            }
        }
    }
}