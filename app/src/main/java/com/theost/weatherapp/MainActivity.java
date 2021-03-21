package com.theost.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // добавляем фрагмент
        Fragment myFragment = new GraphFragment();
        fragmentTransaction.add(R.id.container, myFragment);
        fragmentTransaction.commit();
    }

}