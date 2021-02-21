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


public class ToyAdapter extends FirebaseRecyclerAdapter<
        Toy, ToyAdapter.toysViewholder> {
    DatabaseReference mbase;
    public ToyAdapter(
            @NonNull FirebaseRecyclerOptions<Toy> options)
    {
        super(options);
    }
    @NonNull
    @Override
    public ToyAdapter.toysViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toy, parent, false);
        return new ToyAdapter.toysViewholder(view);
    }
    @Override
    protected void onBindViewHolder(@NonNull ToyAdapter.toysViewholder holder, int position, @NonNull Toy model) {
        holder.age.setText(model.age);
        holder.type.setText(model.type);
        holder.email.setText(model.Email);
        holder.phone.setText(model.Phone);
        holder.username.setText(model.Username);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age, type, username, email, phone;
                TextView a = v.findViewById(R.id.age);
                TextView t = v.findViewById(R.id.type);
                TextView un = v.findViewById(R.id.username);
                TextView e = v.findViewById(R.id.email);
                TextView p = v.findViewById(R.id.phone);
                age = a.getText().toString();
                type = t.getText().toString();
                username = un.getText().toString();
                email = e.getText().toString();
                phone = p.getText().toString();
                Intent intent = new Intent(v.getContext(), ShowToyActivity.class);
                intent.putExtra("Age",age);
                intent.putExtra("Type",type);
                intent.putExtra("Username",username);
                intent.putExtra("Email",email);
                intent.putExtra("Phone",phone);
                v.getContext().startActivity(intent);
            }
        });
    }
    class toysViewholder extends RecyclerView.ViewHolder {
        TextView age, type, username, email, phone;
        public toysViewholder(@NonNull View itemView)
        {
            super(itemView);
            age = itemView.findViewById(R.id.age);
            type = itemView.findViewById(R.id.type);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
