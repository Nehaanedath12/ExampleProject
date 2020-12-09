package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    PieChart pieChart;
    List<PieEntry>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChart=findViewById(R.id.pie_chart);
        list=new ArrayList<>();

        list.add(new PieEntry(200,"2002"));
        list.add(new PieEntry(300,"2003"));
        list.add(new PieEntry(400,"2004"));
        list.add(new PieEntry(500,"2005"));
        list.add(new PieEntry(600,"2006"));
        list.add(new PieEntry(700,"2007"));
        list.add(new PieEntry(10,"2008"));


        PieDataSet dataSet=new PieDataSet(list,"lists");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);

        PieData pieData=new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Values");
        pieChart.animate();

    }
}