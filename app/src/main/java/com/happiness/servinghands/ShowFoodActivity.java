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

public class ShowFoodActivity extends AppCompatActivity {
    TextView peop, typ, exp, em, ph;
    Button delete;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_food);
        peop = findViewById(R.id.people);
        typ = findViewById(R.id.type);
        exp = findViewById(R.id.expiry);
        em = findViewById(R.id.email);
        ph = findViewById(R.id.phone);
        final String people, type, expiry;
        people = getIntent().getStringExtra("No.OfPeople");
        type = getIntent().getStringExtra("Type");
        expiry = getIntent().getStringExtra("Expiry");
        String Email = getIntent().getStringExtra("Email");
        String Phone = getIntent().getStringExtra("Phone");
        em.setText(Email);
        ph.setText(Phone);
        peop.setText(people);
        typ.setText(type);
        exp.setText(expiry);
    }
}
