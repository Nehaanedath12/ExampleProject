package com.example.exampleproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exampleproject.CaptureImage.CapturingImageActivity;
import com.example.exampleproject.ImageOneByOne.PickImageActivity;
import com.example.exampleproject.LoadallImage.MainActivity;
import com.example.exampleproject.Map.MapActivity;
import com.google.android.material.snackbar.Snackbar;

public class Home extends AppCompatActivity {
    Button all, one, mapText, captureImage, network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        all = findViewById(R.id.all_image);
        one = findViewById(R.id.one_by_one);
        mapText = findViewById(R.id.mapText);
        captureImage = findViewById(R.id.capture_image);
        network = findViewById(R.id.network);

        network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tools.isConnected(getApplicationContext())) {
                    Toast.makeText(Home.this, "You have internet", Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar snackbar = Snackbar.make(v, "Please connect to network!! ", Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.show();
                }
            }
        });
        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CapturingImageActivity.class));
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PickImageActivity.class));
            }
        });

        mapText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
            }
        });


    }
}