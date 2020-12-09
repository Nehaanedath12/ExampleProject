package com.example.exampleproject.GraphicalRep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.example.exampleproject.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;
import java.util.List;

public class CandleStickChartActivity extends AppCompatActivity {


    CandleStickChart stickChart;
    List<CandleEntry>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle_stick_chart);
        stickChart=findViewById(R.id.candleChart);

        list=new ArrayList<>();
        list.add(new CandleEntry(0, 4.62f, 2.02f, 2.70f, 4.13f));
        list.add(new CandleEntry(1, 5.50f, 2.70f, 3.35f, 4.96f));
        list.add(new CandleEntry(2, 5.25f, 3.02f, 3.50f, 4.50f));
        list.add(new CandleEntry(3, 6f,    3.25f, 4.40f, 5.0f));
        list.add(new CandleEntry(4, 5.57f, 2f,    2.80f, 4.5f));

        CandleDataSet dataSet = new CandleDataSet(list, "Entries");
        dataSet.setColor(Color.rgb(80, 80, 80));
        dataSet.setShadowColor(Color.DKGRAY);
        dataSet.setShadowWidth(0.7f);
        dataSet.setDecreasingColor(Color.RED);
        dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        dataSet.setIncreasingColor(Color.rgb(122, 242, 84));
        dataSet.setIncreasingPaintStyle(Paint.Style.STROKE);
        dataSet.setNeutralColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);

        CandleData cd = new CandleData(dataSet);

        stickChart.setData(cd);
        stickChart.invalidate();

    }
}