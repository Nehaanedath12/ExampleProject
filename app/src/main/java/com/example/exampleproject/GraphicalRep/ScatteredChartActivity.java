package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ScatteredChartActivity extends AppCompatActivity {

    ScatterChart scatterChart;
    List<Entry>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scattered_chart);
        scatterChart=findViewById(R.id.scattered_chart);

        list=new ArrayList<>();
        list.add(new Entry(2,5));
        list.add(new Entry(3,6));
        list.add(new Entry(4,3));

        ScatterDataSet dataSet=new ScatterDataSet(list,"lists");
//        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);


        ScatterData scatterData=new ScatterData(dataSet);
        scatterChart.setData(scatterData);
        scatterChart.getDescription().setEnabled(false);

    }
}