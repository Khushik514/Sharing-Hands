package com.happiness.servinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
        final String NoofPeople = no.getEditText().getText().toString();
        final String type = foodtype.getEditableText().toString();
        final String expiry = expirydate.getEditableText().toString();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        final String userID = currentUser.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile!=null){
                    String username = userProfile.username;
                    String email = userProfile.email;
                    String phone = userProfile.phoneNo;
                    Food food = new Food(NoofPeople, type, expiry, userID, username, email, phone);
                    FirebaseDatabase.getInstance().getReference("Posts/Food")
                            .push()
                            .setValue(food).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FoodActivity.this,"Post added Successfully!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(FoodActivity.this,"Something went wrong, Try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FoodActivity.this,"Something went wrong, Try again", Toast.LENGTH_LONG).show();
            }
        });

    }
}
