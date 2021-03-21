package com.theost.weatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {

    private static final String ARG_PARAM1 = "MONTH";
    private static final String ARG_PARAM2 = "DAY";
    private static final String ARG_PARAM3 = "WEEK";
    private static final String ARG_PARAM4 = "TEMPS_DAY";
    private static final String ARG_PARAM5 = "NAME";
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private float[] mParam4;
    private String mParam5;

    private LineDataSet dataSet;
    private LineData lineData;
    private DATA[] dataObjects;
    private List<Entry> entries;

    private ArrayList<Float> dataFloat;

    public GraphFragment() {}

    public static GraphFragment newInstance(String param1, String param2, String param3, float[] param4, String param5) {
        GraphFragment fragment = new GraphFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putFloatArray(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getFloatArray(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_graph, container, false);
        LineChart chart = (LineChart) layout.findViewById(R.id.chart);
        TextView tv = layout.findViewById(R.id.textView);
        tv.setText(mParam5);
        MaterialButtonToggleGroup toggleButtons = layout.findViewById(R.id.toggleButton);

        dataFloat = new ArrayList<>();
        for (float f : mParam4) {
            dataFloat.add(f);
        }
        dataObjects = new DATA[dataFloat.size()];
        for (int i = 0; i < dataFloat.size(); i++) {
            dataObjects[i] = new DATA(i, dataFloat.get(i));
        }
        entries = new ArrayList<Entry>();
        for (DATA data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }
        dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(R.color.design_default_color_on_primary);
        dataSet.setValueTextColor(R.color.design_default_color_primary_dark); // styling, ...
        lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
        
        toggleButtons.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            int pr = 0;
            ArrayList<Float> newArr;
            switch (checkedId) {
                case R.id.button1:
                    pr = 0;
                    newArr = new ArrayList<>();
//                    for (int i = 0; i < dataFloat.size(); i++) {
//                        pr += dataFloat.get(i);
//                        if (i % 7 == 0) {
//                            newArr.add(pr / 7f);
//                            pr = 0;
//                        }
//                    }

                        newArr.add(dataFloat.get(0));


                    dataObjects = new DATA[newArr.size()];
                    for (int i = 0; i < newArr.size(); i++) {
                        dataObjects[i] = new DATA(i, newArr.get(i));
                    }
                    entries = new ArrayList<Entry>();
                    for (DATA data : dataObjects) {
                        // turn your data into Entry objects
                        entries.add(new Entry(data.getValueX(), data.getValueY()));
                    }
                    dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
                    lineData = new LineData(dataSet);
                    chart.setData(lineData);
                    chart.invalidate(); // refresh
                    break;
                case R.id.button2:
                    pr = 0;
                    newArr = new ArrayList<>();
//                    for (int i = 0; i < dataFloat.size(); i++) {
//                        pr += dataFloat.get(i);
//                        if (i % 7 == 0) {
//                            newArr.add(pr / 7f);
//                            pr = 0;
//                        }
//                    }
                    for (int i = 0; i < 7; i++) {
                        newArr.add(dataFloat.get(i));
                    }

                    dataObjects = new DATA[newArr.size()];
                    for (int i = 0; i < newArr.size(); i++) {
                        dataObjects[i] = new DATA(i, newArr.get(i));
                    }
                    entries = new ArrayList<Entry>();
                    for (DATA data : dataObjects) {
                        // turn your data into Entry objects
                        entries.add(new Entry(data.getValueX(), data.getValueY()));
                    }
                    dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
                    lineData = new LineData(dataSet);
                    chart.setData(lineData);
                    chart.invalidate(); // refresh
                    break;
                case R.id.button3:
                    pr = 0;
                    newArr = new ArrayList<>();
//                    for (int i = 0; i < dataFloat.size(); i++) {
//                        pr += dataFloat.get(i);
//                        if (i % 30 == 0) {
//                            newArr.add(pr / 30f);
//                            pr = 0;
//                        }
//                    }

                    for (int i = 0; i < 31; i++) {
                        newArr.add(dataFloat.get(i));
                    }

                    dataObjects = new DATA[newArr.size()];
                    for (int i = 0; i < newArr.size(); i++) {
                        dataObjects[i] = new DATA(i, newArr.get(i));
                    }
                    entries = new ArrayList<Entry>();
                    for (DATA data : dataObjects) {
                        // turn your data into Entry objects
                        entries.add(new Entry(data.getValueX(), data.getValueY()));
                    }
                    dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
                    lineData = new LineData(dataSet);
                    chart.setData(lineData);
                    chart.invalidate(); // refresh
                    break;
            }
        });
        return layout;
    }
    class DATA {
        float X;
        float Y;

        public DATA(float x, float y) {
            X = x;
            Y = y;
        }

        float getValueX() {
            return X;
        }
        float getValueY() {
            return Y;
        }
    }
}