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

public class ShowClothDonorActivity extends AppCompatActivity {
    TextView ag, gen, seas, em, ph;
    Button delete;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_cloth_donor);
        ag = findViewById(R.id.age);
        gen = findViewById(R.id.gender);
        seas = findViewById(R.id.season);
        delete = findViewById(R.id.delete);
        final String age, gender, season;
        age = getIntent().getStringExtra("Ages");
        gender = getIntent().getStringExtra("Gender");
        season = getIntent().getStringExtra("Season");
        ag.setText(age);
        gen.setText(gender);
        seas.setText(season);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbase = FirebaseDatabase.getInstance().getReference("Posts/Cloth");
                mbase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot d: snapshot.getChildren()){
                                if(d.child("age").getValue().toString().equalsIgnoreCase(age)&&d.child("gender").getValue().toString().equalsIgnoreCase(gender)&&d.child("season").getValue().toString().equalsIgnoreCase(season)){
                                    String key = d.getKey();
                                    mbase.child(key).removeValue();
                                    Toast.makeText(getApplicationContext(),"Deleting..",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),ClothPostsDonorActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
