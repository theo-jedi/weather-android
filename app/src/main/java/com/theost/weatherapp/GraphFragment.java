package com.theost.weatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class GraphFragment extends Fragment {

    private static final String ARG_PARAM1 = "MONTH";
    private static final String ARG_PARAM2 = "DAY";
    private static final String ARG_PARAM3 = "WEEK";

    private String mParam1;
    private String mParam2;
    private String mParam3;

    public GraphFragment() {}

    public static GraphFragment newInstance(String param1, String param2, String param3) {
        GraphFragment fragment = new GraphFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_graph, container, false);
//        TextView tv = layout.findViewById(R.id.textView);
//        MaterialButtonToggleGroup toggleButtons = layout.findViewById(R.id.toggleButton);
//
//        toggleButtons.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
//                 toggleButtons.setSingleSelection(checkedId);
//                 switch (checkedId) {
//                     case R.id.button1:
//                         break;
//                 }
//        });
        return layout;
    }
}