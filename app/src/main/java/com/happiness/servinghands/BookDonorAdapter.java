package com.happiness.servinghands;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;


public class BookDonorAdapter extends FirebaseRecyclerAdapter<
        Book, BookDonorAdapter.booksViewholder> {
    DatabaseReference mbase;
    public BookDonorAdapter(
            @NonNull FirebaseRecyclerOptions<Book> options)
    {
        super(options);
    }

    @NonNull
    @Override
    public booksViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false);
        return new BookDonorAdapter.booksViewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookDonorAdapter.booksViewholder holder, int position, @NonNull Book model) {
        holder.bookname.setText(model.BookName);
        holder.authorname.setText(model.AuthorName);
        holder.category.setText(model.BookCategory);
        holder.ages.setText(model.BookAges);
        holder.email.setText(model.Email);
        holder.phone.setText(model.Phone);
        holder.username.setText(model.Username);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname, authorname, category, ages, bookid, username, email, phone;
                TextView bn = v.findViewById(R.id.bookname);
                TextView an = v.findViewById(R.id.authorname);
                TextView c = v.findViewById(R.id.category);
                TextView a = v.findViewById(R.id.ages);
                TextView un = v.findViewById(R.id.username);
                TextView e = v.findViewById(R.id.email);
                TextView p = v.findViewById(R.id.phone);
                bookname = bn.getText().toString();
                authorname = an.getText().toString();
                category = c.getText().toString();
                ages = a.getText().toString();
                username = un.getText().toString();
                email = e.getText().toString();
                phone = p.getText().toString();
                Intent intent = new Intent(v.getContext(), ShowBookDonorActivity.class);
                intent.putExtra("BookName",bookname);
                intent.putExtra("AuthorName",authorname);
                intent.putExtra("Category",category);
                intent.putExtra("Ages",ages);
                intent.putExtra("Username",username);
                intent.putExtra("Email",email);
                intent.putExtra("Phone",phone);
                v.getContext().startActivity(intent);
            }
        });
    }

    class booksViewholder
            extends RecyclerView.ViewHolder {
        TextView bookname, authorname, category, ages, username, email, phone;
        public booksViewholder(@NonNull View itemView)
        {
            super(itemView);

            bookname = itemView.findViewById(R.id.bookname);
            authorname = itemView.findViewById(R.id.authorname);
            category = itemView.findViewById(R.id.category);
            ages = itemView.findViewById(R.id.ages);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
