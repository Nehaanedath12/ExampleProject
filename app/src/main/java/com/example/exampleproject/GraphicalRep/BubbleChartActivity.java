package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BubbleChartActivity extends AppCompatActivity {

    BubbleChart bubbleChart;
    List<BubbleEntry>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_chart);
        bubbleChart=findViewById(R.id.bubbleChart);

        list=new ArrayList<>();

        list.add(new BubbleEntry(0, 1,0.21f));
        list.add(new BubbleEntry(1, 2,0.12f));
        list.add(new BubbleEntry(2, 3,0.20f));
        list.add(new BubbleEntry(2, 4,0.52f));
        list.add(new BubbleEntry(3, 5,0.29f));
        list.add(new BubbleEntry(4, 6,0.62f));

        BubbleDataSet dataSet=new BubbleDataSet(list,"Bubble Data");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10f);


        BubbleData barData=new BubbleData(dataSet);
        bubbleChart.setData(barData);
        bubbleChart.getDescription().setEnabled(false);
    }
}