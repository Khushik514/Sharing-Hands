package com.happiness.servinghands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MypostsActivity extends AppCompatActivity {
    Button newpost, book, food, cloth, toy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_myposts);
        newpost = findViewById(R.id.newpost);
        book = findViewById(R.id.books);
        cloth = findViewById(R.id.clothes);
        toy = findViewById(R.id.toys);
        food = findViewById(R.id.food);
        newpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypostsActivity.this, DonationTypeActivity.class);
                startActivity(intent);
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypostsActivity.this, BookPostsActivity.class);
                startActivity(intent);
            }
        });
        cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypostsActivity.this, ClothPostsActivity.class);
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypostsActivity.this, FoodPostsActivity.class);
                startActivity(intent);
            }
        });
        toy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MypostsActivity.this, ToyPostsActivity.class);
                startActivity(intent);
            }
        });
    }
}
