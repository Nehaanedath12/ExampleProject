package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LineChartActivity extends AppCompatActivity {

    LineChart lineChart;
    List<Entry>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        lineChart=findViewById(R.id.line_chart);
        list=new ArrayList<>();


        list.add(new Entry(200,100));
        list.add(new Entry(300,300));
        list.add(new Entry(400,500));
        list.add(new Entry(500,700));
        list.add(new Entry(600,900));

        LineDataSet dataSet=new LineDataSet(list,"lists");
//        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);

        LineData lineData=new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);

    }
}