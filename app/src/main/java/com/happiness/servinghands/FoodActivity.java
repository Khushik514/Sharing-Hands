package com.happiness.servinghands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class FoodActivity extends AppCompatActivity {
    TextInputLayout no;
    AutoCompleteTextView foodtype, expirydate;
    Button submit;
    String[] dates = {"< 3 days","3 days","3 weeks","3 months","> 3 months"};
    String[] types = {"Instant","Raw","Packaged","Cooked"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food);
        no = findViewById(R.id.no_of_people);
        foodtype = findViewById(R.id.type);
        expirydate = findViewById(R.id.expiry);
        ArrayAdapter typeAdapter = new ArrayAdapter(this, R.layout.list_item, types);
        ArrayAdapter dateAdapter = new ArrayAdapter(this, R.layout.list_item, dates);
        foodtype.setAdapter(typeAdapter);
        expirydate.setAdapter(dateAdapter);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodAdd();
            }
        });
    }
    public void foodAdd(){
        String NoofPeople = no.getEditText().getText().toString();
        String type = foodtype.getEditableText().toString();
        String expiry = expirydate.getEditableText().toString();
        Food food = new Food(NoofPeople, type, expiry);

    }
}
