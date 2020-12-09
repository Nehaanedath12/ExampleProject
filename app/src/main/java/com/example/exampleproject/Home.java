package com.example.exampleproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exampleproject.CaptureImage.CapturingImageActivity;
import com.example.exampleproject.GraphicalRep.BubbleChartActivity;
import com.example.exampleproject.GraphicalRep.CandleStickChartActivity;
import com.example.exampleproject.GraphicalRep.GraphicalActivity;
import com.example.exampleproject.GraphicalRep.LineChartActivity;
import com.example.exampleproject.GraphicalRep.PieChartActivity;
import com.example.exampleproject.GraphicalRep.RadarChartActivity;
import com.example.exampleproject.GraphicalRep.ScatteredChartActivity;
import com.example.exampleproject.ImageOneByOne.PickImageActivity;
import com.example.exampleproject.LoadallImage.MainActivity;
import com.example.exampleproject.Map.MapActivity;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.google.android.material.snackbar.Snackbar;
import com.shasin.notificationbanner.Banner;

import es.dmoral.toasty.Toasty;

public class Home extends AppCompatActivity {
    Button all, one, mapText, captureImage, network,graph,pieGraph,
            radar_graph_Home,line_graph_Home,bubbleChart,candle_graph_Home,scattered_graph_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        all = findViewById(R.id.all_image);
        one = findViewById(R.id.one_by_one);
        mapText = findViewById(R.id.mapText);
        captureImage = findViewById(R.id.capture_image);
        network = findViewById(R.id.network);
        graph=findViewById(R.id.graph_Home);
        pieGraph=findViewById(R.id.pie_graph_Home);
        radar_graph_Home=findViewById(R.id.radar_graph_Home);
        line_graph_Home=findViewById(R.id.line_graph_Home);
        bubbleChart=findViewById(R.id.bubble_graph_Home);
        candle_graph_Home=findViewById(R.id.candle_graph_Home);
        scattered_graph_Home=findViewById(R.id.scattered_graph_Home);

        network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tools.isConnected(getApplicationContext())) {
                    Toasty.success(Home.this, "You have internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toasty.error(Home.this,"No Internet!!",Toast.LENGTH_LONG).show();
                    Banner.make(v,Home.this,Banner.INFO,"not success",Banner.TOP).show();
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
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GraphicalActivity.class));
            }
        });
        pieGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });
        radar_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RadarChartActivity.class));
            }
        });
        line_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LineChartActivity.class));
            }
        });
        bubbleChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BubbleChartActivity.class));
            }
        });
        candle_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CandleStickChartActivity.class));
            }
        });
        scattered_graph_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScatteredChartActivity.class));
            }
        });
    }
}