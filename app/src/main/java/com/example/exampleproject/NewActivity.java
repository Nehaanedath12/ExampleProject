package com.example.exampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;


import uk.co.senab.photoview.PhotoViewAttacher;

public class NewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        ImageView imageView=findViewById(R.id.single_image);
        File file= new File(getIntent().getStringExtra("position"));
        PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(imageView);
        photoViewAttacher.update();
        Glide.with(getApplicationContext()).load(file).into(imageView);



    }
}