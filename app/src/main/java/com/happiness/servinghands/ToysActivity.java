package com.happiness.servinghands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class ToysActivity extends AppCompatActivity {
    AutoCompleteTextView age, type;
    String[] ages = {"< 3 years","3 - 5 years","5 - 8 years","8 - 12 years","12+ years"};
    String[] types = {"Electronic","Playsets & Figures","Pretend Play","Educational"};
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_toys);
        age = findViewById(R.id.age);
        type = findViewById(R.id.type);
        submit = findViewById(R.id.submit);
        ArrayAdapter ageAdapter = new ArrayAdapter(this, R.layout.list_item, ages);
        ArrayAdapter typeAdapter = new ArrayAdapter(this, R.layout.list_item, types);
        age.setAdapter(ageAdapter);
        type.setAdapter(typeAdapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toyAdd();
            }
        });
    }
    public void toyAdd(){
        String suitableForAges = age.getEditableText().toString();
        String toyType = type.getEditableText().toString();
        Toy toy = new Toy(suitableForAges, toyType);

    }
}
