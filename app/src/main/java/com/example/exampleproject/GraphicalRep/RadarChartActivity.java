package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class RadarChartActivity extends AppCompatActivity {

    RadarChart radarChart;
    List<RadarEntry>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        radarChart=findViewById(R.id.radar_chart);

        list=new ArrayList<>();
        list.add(new RadarEntry(440));
        list.add(new RadarEntry(520));
        list.add(new RadarEntry(609));
        list.add(new RadarEntry(789));
        list.add(new RadarEntry(678));


        RadarDataSet dataSet=new RadarDataSet(list,"radar");
        dataSet.setColors(Color.RED);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);

        RadarData radarData=new RadarData(dataSet);

        String[]labels={"2000","2001","2002","2003","2004"};
        XAxis xAxis=radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setData(radarData);


    }
}