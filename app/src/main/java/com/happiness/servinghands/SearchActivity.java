package com.happiness.servinghands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        type = findViewById(R.id.donationType);
        String[] types = {"Books","Clothes","Food","Toys"};
       ArrayAdapter typeAdapter = new ArrayAdapter(this, R.layout.list_item, types);
        type.setAdapter(typeAdapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.getEditableText().toString().equalsIgnoreCase("Toys"))
                {

                    Intent intent = new Intent(SearchActivity.this, ToyPostsActivity.class);
                    startActivity(intent);
                }
                else if(type.getEditableText().toString().equalsIgnoreCase("Clothes"))
                {

                    Intent intent = new Intent(SearchActivity.this, ClothPostsActivity.class);
                    startActivity(intent);
                }
                else if(type.getEditableText().toString().equalsIgnoreCase("Food"))
                {

                    Intent intent = new Intent(SearchActivity.this, FoodPostsActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(SearchActivity.this, BookPostsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
