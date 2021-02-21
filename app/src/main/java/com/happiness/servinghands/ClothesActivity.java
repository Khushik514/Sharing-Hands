package com.happiness.servinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClothesActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
        final String suitable_age = age.getEditableText().toString();
        final String genderspec = gender.getEditableText().toString();
        final String seasoninfo = season.getEditableText().toString();
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
                    Cloth cloth = new Cloth(suitable_age, genderspec, seasoninfo, userID, username, email, phone);
                    FirebaseDatabase.getInstance().getReference("Posts/Cloth")
                            .push()
                            .setValue(cloth).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ClothesActivity.this,"Post added Successfully!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MypostsActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(ClothesActivity.this,"Something went wrong, Try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ClothesActivity.this,"Something went wrong, Try again", Toast.LENGTH_LONG).show();
            }
        });

    }
}
