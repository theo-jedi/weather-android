package com.theost.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.city_listview);

        // определяем строковый массив
        final String[] catNames = new String[] {
                "Алмазный", "Западный", "Курортный", "Лесной", "Научный",
                "Полярный", "Портовый", "Приморский", "Садовый", "Северный",
                "Степной", "Таежный", "Южный"
        };

        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);

        listView.setAdapter(adapter);
    }

}