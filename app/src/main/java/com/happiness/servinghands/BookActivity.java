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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BookActivity extends AppCompatActivity {
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
        String AuthorName = author.getEditText().getText().toString();
        String BookName = name.getEditText().getText().toString();
        String BookCategory = category.getEditableText().toString();
        String BookAges = age.getEditableText().toString();
        Book book = new Book(BookName, AuthorName, BookCategory, BookAges);

    }
}
