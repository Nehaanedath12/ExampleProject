package com.example.exampleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int request_code =121;
    List<PhotoClass>list;
    RecyclerView recyclerView;
    PhotoAdapter adapter;
    TextView size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        size=findViewById(R.id.size);
        recyclerView=findViewById(R.id.recyclerView);
        list=new ArrayList<>();
        adapter=new PhotoAdapter(this,list);
        GridLayoutManager manager=new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, request_code);
        }
        else {
            image();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode== request_code){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

                image();
            }
            else {Toast.makeText(this, "want permission", Toast.LENGTH_SHORT).show();}
        }
    }

    @SuppressLint("SetTextI18n")
    private void image() {

        String[] column={MediaStore.Images.Media.DATA,MediaStore.Images.Media._ID};
        String orderBy= MediaStore.Images.Media.DATE_MODIFIED;
        Cursor cursor=getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,column,null,null,orderBy+" DESC");

        int totalImages=cursor.getCount();
        size.setText("("+totalImages+")");
        String [] path=new String[totalImages] ;
        for (int i=0;i<totalImages;i++){
            cursor.moveToNext();
            int index=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            path[i]=cursor.getString(index);
            PhotoClass photoClass=new PhotoClass(path[i]);
            list.add(photoClass);
        }
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        cursor.close();

    }
}