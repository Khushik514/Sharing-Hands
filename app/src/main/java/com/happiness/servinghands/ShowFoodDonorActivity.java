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

public class ShowFoodDonorActivity extends AppCompatActivity {
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
        delete = findViewById(R.id.delete);
        final String people, type, expiry;
        people = getIntent().getStringExtra("No.OfPeople");
        type = getIntent().getStringExtra("Type");
        expiry = getIntent().getStringExtra("Expiry");
        peop.setText(people);
        typ.setText(type);
        exp.setText(expiry);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbase = FirebaseDatabase.getInstance().getReference("Posts/Food");
                mbase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot d: snapshot.getChildren()){
                                if(d.child("noOfPeople").getValue().toString().equalsIgnoreCase(people)&&d.child("type").getValue().toString().equalsIgnoreCase(type)&&d.child("expiry").getValue().toString().equalsIgnoreCase(expiry)){
                                    String key = d.getKey();
                                    mbase.child(key).removeValue();
                                    Toast.makeText(getApplicationContext(),"Deleting..",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),FoodPostsDonorActivity.class);
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
