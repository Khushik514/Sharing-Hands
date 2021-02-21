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

public class ShowBookDonorActivity extends AppCompatActivity {
    TextView bookn, authn, cat, age, em, ph;
    Button delete;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_book_donor);
        bookn = findViewById(R.id.bookname);
        authn = findViewById(R.id.authorname);
        cat = findViewById(R.id.category);
        age = findViewById(R.id.ages);
        final String BookName = getIntent().getStringExtra("BookName");
        String AuthorName = getIntent().getStringExtra("AuthorName");
        String Category = getIntent().getStringExtra("Category");
        String Ages = getIntent().getStringExtra("Ages");
        //String Username = getIntent().getStringExtra("Username");
        bookn.setText(BookName);
        authn.setText(AuthorName);
        cat.setText(Category);
        age.setText(Ages);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbase = FirebaseDatabase.getInstance().getReference("Posts/Book");
                mbase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot d: snapshot.getChildren()){
                                if(d.child("BookName").getValue().toString().equalsIgnoreCase(BookName)){
                                    String key = d.getKey();
                                    mbase.child(key).removeValue();
                                    Toast.makeText(getApplicationContext(),"Deleting..",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),BookPostsDonorActivity.class);
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
