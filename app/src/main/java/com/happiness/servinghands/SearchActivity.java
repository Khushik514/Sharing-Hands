package com.happiness.servinghands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {
    Button search;
    AutoCompleteTextView city, type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        search = findViewById(R.id.search);
        city = findViewById(R.id.city);
        type = findViewById(R.id.donationType);
        String[] cities = {"Mumbai","Delhi","Kanpur","Bengaluru","Kolkata","Chennai","Lucknow"};
        String[] types = {"Books","Clothes","Food","Toys"};
        ArrayAdapter cityAdapter = new ArrayAdapter(this, R.layout.list_item, cities);
        city.setAdapter(cityAdapter);
        ArrayAdapter typeAdapter = new ArrayAdapter(this, R.layout.list_item, types);
        type.setAdapter(typeAdapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
