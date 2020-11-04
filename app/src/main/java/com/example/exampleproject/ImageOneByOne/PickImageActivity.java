package com.example.exampleproject.ImageOneByOne;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exampleproject.R;

import java.util.ArrayList;
import java.util.List;

public class PickImageActivity extends AppCompatActivity {
    List<ImageClass> list;
    ImageAdapter adapter;
    Button load;
    RecyclerView RV_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);
        load = findViewById(R.id.loadImage);
        RV_imageView = findViewById(R.id.recycler_Image);
        list = new ArrayList<>();
        adapter = new ImageAdapter(getApplicationContext(), list);

        GridLayoutManager manager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        RV_imageView.setLayoutManager(manager);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageClass photoClass = new ImageClass(data.getData());
        list.add(photoClass);
        RV_imageView.setAdapter(adapter);
//        Glide.with(PickImageActivity.this).load(data.getData()).into(imageView);

    }
}