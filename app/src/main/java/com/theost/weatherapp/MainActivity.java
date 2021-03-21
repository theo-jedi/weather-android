package com.theost.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.database.Cursor;
import android.os.Bundle;

import com.theost.weatherapp.utils.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String[] cities = new String[] {
            "Алмазный", "Западный", "Курортный", "Лесной", "Научный",
            "Полярный", "Портовый", "Приморский", "Садовый", "Северный",
            "Степной", "Таежный", "Южный"
    };

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        if (true) {
            for (String city : cities) {
                try {
                    databaseHelper.importData(city + ".csv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // получаем экземпляр FragmentTransaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        for (String s : cities) {
            ArrayList<Float> dots = new ArrayList<>();

            Cursor data = databaseHelper.getYearData(s);
            if (data != null) {
                while (data.moveToNext()) {
                    String dayTemp = data.getString(data.getColumnIndex("TEMP"));
                    float day = Float.parseFloat(dayTemp);
                    dots.add(day);
                }
            }

            Fragment myFragment = new GraphFragment();
            Bundle bundle = new Bundle();
            bundle.putString("DAY", "12");
            bundle.putString("MONTH", "11");
            bundle.putString("YEAR", "1001");
            bundle.putString("NAME", s);
            int i = 0;
           float[] temps = new float[dots.size()];
            for (float d : dots) {
                temps[i] = d;
                i++;
            }

            bundle.putFloatArray("TEMPS_DAY", temps);
            myFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.container, myFragment);
        }
        fragmentTransaction.commit();
    }

}