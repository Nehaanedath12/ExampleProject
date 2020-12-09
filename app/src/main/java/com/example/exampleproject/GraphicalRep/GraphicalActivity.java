package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GraphicalActivity extends AppCompatActivity {
    List<BarEntry> barChartList;
    List<PieEntry>pieEntryList;
    BarChart barChart;
    PieChart pieChart;
    Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical);
         barChart=findViewById(R.id.bar_chart);
         pieChart=findViewById(R.id.pie_chart);

        barChartList=new ArrayList<>();
        pieEntryList=new ArrayList<>();
        values=new Values();
        values.setY_axis(66);
        values.setX_axis(66);

        barData();
//        pieData();

    }

    private void pieData() {
        pieEntryList.add(new PieEntry(40,80));
        PieDataSet pieDataSet=new PieDataSet(pieEntryList,"pie");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        Description description=new Description();
        description.setText("pie rate");
        pieChart.setDescription(description);
        pieChart.invalidate();


    }

    private void barData() {
        barChartList.add(new BarEntry(values.getX_axis(),values.getY_axis()));
        BarDataSet barDataSet=new BarDataSet(barChartList,"bar");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.09f);

//        barChart.animateY(5000);
        barChart.setData(barData);
        barChart.setFitBars(true);

        Description description=new Description();
        description.setText("growth rate");
        barChart.setDescription(description);
        barChart.invalidate();
    }
}