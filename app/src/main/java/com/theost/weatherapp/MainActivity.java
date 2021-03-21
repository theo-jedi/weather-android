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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String[] cities = new String[] {
            "Алмазный", "Западный", "Курортный", "Лесной", "Научный",
            "Полярный", "Портовый", "Приморский", "Садовый", "Северный",
            "Степной", "Таежный", "Южный"
    };

    private DatabaseHelper databaseHelper;

    private Random random = new Random();

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

//        Cursor dataa = databaseHelper.getMonthData("Алмазный", 1);
//        if (dataa != null) {
//            int count = 1;
//           if (dataa.moveToFirst()) {
//                String dayTemp = dataa.getString(dataa.getColumnIndex("TEMP"));
//               count += 1;
//           }
//        }

//        ListView listView = findViewById(R.id.city_listview);
//
//        // определяем строковый массив
//        final String[] catNames = new String[] {
//                "Алмазный", "Западный", "Курортный", "Лесной", "Научный",
//                "Полярный", "Портовый", "Приморский", "Садовый", "Северный",
//                "Степной", "Таежный", "Южный"
//        };
//
//        // используем адаптер данных
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, catNames);
//
//        listView.setAdapter(adapter);

//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft;
//
//        ft = fm.beginTransaction();
//        Fragment fragment = new GraphFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("DAY", "12");
//        bundle.putString("MONTH", "11");
//        bundle.putString("YEAR", "1001");
//        fragment.setArguments(bundle);
//        ft.replace(R.id.container, fragment);
//        ft.commit();

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
//            float[] temps = new float[365];
//            for (int i = 0; i < 365; i++) {
//                temps[i] = random.nextFloat() * 12 - 4;
//            }
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