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

public class ClothPostsDonorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ClothDonorAdapter adapter;
    DatabaseReference mbase;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cloth_posts_donor);
        mbase = FirebaseDatabase.getInstance().getReference("Posts/Cloth");
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Cloth> options
                = new FirebaseRecyclerOptions.Builder<Cloth>()
                .setQuery(mbase, new SnapshotParser<Cloth>() {
                    @NonNull
                    @Override
                    public Cloth parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Cloth cloth = snapshot.getValue(Cloth.class);
                        if(cloth.UID.equalsIgnoreCase(mAuth.getCurrentUser().getUid()))
                            return cloth;
                        else
                            return new Cloth();
                    }
                }).build();
        adapter = new ClothDonorAdapter(options);
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
