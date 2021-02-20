package com.happiness.servinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class BookActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextInputLayout name, author;
    AutoCompleteTextView category, age;
    String[] categories = {"Children's & Young Adult","Health, Family & Personal Development","Business Self-Help",
            "Literature & Fiction","Crafts, Hobbies & Home","New Age & Spirituality","Higher Education Textbooks",
            "Biographies, Diaries & True Accounts"};
    String[] ages = {"<3","3-5","5-10","10-15","15-18","20-25","25+"};
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book);
        name = findViewById(R.id.book_name);
        author = findViewById(R.id.book_author);
        category = findViewById(R.id.category);
        age = findViewById(R.id.age);
        ArrayAdapter categoryAdapter = new ArrayAdapter(this, R.layout.list_item, categories);
        category.setAdapter(categoryAdapter);
        ArrayAdapter ageAdapter = new ArrayAdapter(this, R.layout.list_item, ages);
        age.setAdapter(ageAdapter);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }
    public void addBook(){
        final String AuthorName = author.getEditText().getText().toString();
        final String BookName = name.getEditText().getText().toString();
        final String BookCategory = category.getEditableText().toString();
        final String BookAges = age.getEditableText().toString();
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
                    Book book = new Book(BookName, AuthorName, BookCategory, BookAges, userID, username, email, phone);
                    FirebaseDatabase.getInstance().getReference("Posts/Book")
                            .push()
                            .setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BookActivity.this,"Post added Successfully!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(BookActivity.this,"Something went wrong, Try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BookActivity.this,"Something went wrong, Try again", Toast.LENGTH_LONG).show();
            }
        });

    }
}
