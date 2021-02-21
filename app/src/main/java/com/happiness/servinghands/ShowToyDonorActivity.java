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

public class ShowToyDonorActivity extends AppCompatActivity {
    TextView ages, types, em, ph;
    Button delete;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_toy_donor);
        ages = findViewById(R.id.age);
        types = findViewById(R.id.type);
        delete = findViewById(R.id.delete);
        final String age, type;
        age = getIntent().getStringExtra("Age");
        type = getIntent().getStringExtra("Type");
        ages.setText(age);
        types.setText(type);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbase = FirebaseDatabase.getInstance().getReference("Posts/Toy");
                mbase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot d: snapshot.getChildren()){
                                if(d.child("age").getValue().toString().equalsIgnoreCase(age)&&d.child("type").getValue().toString().equalsIgnoreCase(type)){
                                    String key = d.getKey();
                                    mbase.child(key).removeValue();
                                    Toast.makeText(getApplicationContext(),"Deleting..",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),ToyPostsDonorActivity.class);
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
