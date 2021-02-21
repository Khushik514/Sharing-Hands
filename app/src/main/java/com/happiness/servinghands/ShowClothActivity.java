package com.happiness.servinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowClothActivity extends AppCompatActivity {
    TextView ag, gen, seas, em, ph;
    Button delete;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_cloth);
        ag = findViewById(R.id.age);
        gen = findViewById(R.id.gender);
        seas = findViewById(R.id.season);
        em = findViewById(R.id.email);
        ph = findViewById(R.id.phone);
        final String age, gender, season;
        age = getIntent().getStringExtra("Ages");
        gender = getIntent().getStringExtra("Gender");
        season = getIntent().getStringExtra("Season");
        String Email = getIntent().getStringExtra("Email");
        String Phone = getIntent().getStringExtra("Phone");
        em.setText(Email);
        ph.setText(Phone);
        ag.setText(age);
        gen.setText(gender);
        seas.setText(season);
    }
}
