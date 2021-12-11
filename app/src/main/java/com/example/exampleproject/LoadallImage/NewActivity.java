package com.example.exampleproject.LoadallImage;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.exampleproject.R;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.io.File;

import uk.co.senab.photoview.PhotoViewAttacher;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ImageView imageView = findViewById(R.id.single_image);
        TextView name = findViewById(R.id.name);

        FirebaseCrash.log("exception");
        FirebaseCrash.report(new Exception("oops"));
        try {
            File file = new File(getIntent().getStringExtra("position"));
            name.setText(file.getName());
            Glide.with(getApplicationContext()).load(file).into(imageView);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);
            photoViewAttacher.canZoom();
        }catch (Exception e){
            Log.d("exceptionN",e.getMessage()+e.toString()+NewActivity.this);
        }



    }
}