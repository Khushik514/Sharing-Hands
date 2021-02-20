package com.happiness.servinghands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class ClothesActivity extends AppCompatActivity {
    AutoCompleteTextView age, gender, season;
    Button submit;
    String[] ages = {"< 3 years","3 - 5 years","5 - 10 years","10 - 15 years","15 - 20 years","20+ years"};
    String[] genders = {"None","Male","Female"};
    String[] seasons = {"None","Summers","Winters"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_clothes);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        season = findViewById(R.id.season);
        submit = findViewById(R.id.submit);
        ArrayAdapter ageAdapter = new ArrayAdapter(this, R.layout.list_item, ages);
        ArrayAdapter genderAdapter = new ArrayAdapter(this, R.layout.list_item, genders);
        ArrayAdapter seasonAdapter = new ArrayAdapter(this, R.layout.list_item, seasons);
        age.setAdapter(ageAdapter);
        gender.setAdapter(genderAdapter);
        season.setAdapter(seasonAdapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothAdd();
            }
        });
    }
    public void clothAdd(){
        String suitable_age = age.getEditableText().toString();
        String genderspec = gender.getEditableText().toString();
        String seasoninfo = season.getEditableText().toString();
        Cloth cloth = new Cloth(suitable_age, genderspec, seasoninfo);

    }
}
