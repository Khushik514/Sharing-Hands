package com.happiness.servinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodPostsDonorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FoodDonorAdapter adapter;
    DatabaseReference mbase;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_posts_donor);
        mbase = FirebaseDatabase.getInstance().getReference("Posts/Food");
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Food> options
                = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(mbase, new SnapshotParser<Food>() {
                    @NonNull
                    @Override
                    public Food parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Food food = snapshot.getValue(Food.class);
                        if(food.UID.equalsIgnoreCase(mAuth.getCurrentUser().getUid()))
                            return food;
                        else
                            return new Food();
                    }
                }).build();
        adapter = new FoodDonorAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
