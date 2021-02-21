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

public class ToyPostsDonorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ToyDonorAdapter adapter;
    DatabaseReference mbase;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_toy_posts_donor);
        mbase = FirebaseDatabase.getInstance().getReference("Posts/Toy");
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Toy> options
                = new FirebaseRecyclerOptions.Builder<Toy>()
                .setQuery(mbase, new SnapshotParser<Toy>() {
                    @NonNull
                    @Override
                    public Toy parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Toy toy = snapshot.getValue(Toy.class);
                        if(toy.UID.equalsIgnoreCase(mAuth.getCurrentUser().getUid()))
                            return toy;
                        else
                            return new Toy();
                    }
                }).build();
        adapter = new ToyDonorAdapter(options);
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
