package com.example.exampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

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
        TextView name=findViewById(R.id.name);
        File file= new File(getIntent().getStringExtra("position"));
        name.setText(file.getName());
        Glide.with(getApplicationContext()).load(file).into(imageView);
        PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(imageView);
        photoViewAttacher.canZoom();
    }
}