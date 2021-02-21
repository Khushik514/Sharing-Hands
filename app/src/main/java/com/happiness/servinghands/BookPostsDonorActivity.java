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

public class BookPostsDonorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    BookDonorAdapter adapter;
    DatabaseReference mbase;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book_posts_donor);
        mbase = FirebaseDatabase.getInstance().getReference("Posts/Book");
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Book> options
                = new FirebaseRecyclerOptions.Builder<Book>()
                .setQuery(mbase, new SnapshotParser<Book>() {
                    @NonNull
                    @Override
                    public Book parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Book book = snapshot.getValue(Book.class);
                        if(book.UID.equalsIgnoreCase(mAuth.getCurrentUser().getUid()))
                            return book;
                        else
                            return null;
                    }
                }).build();
        adapter = new BookDonorAdapter(options);
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
