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


public class ClothAdapter extends FirebaseRecyclerAdapter<
        Cloth, ClothAdapter.clothsViewholder> {
    DatabaseReference mbase;
    public ClothAdapter(
            @NonNull FirebaseRecyclerOptions<Cloth> options)
    {
        super(options);
    }
    @NonNull
    @Override
    public ClothAdapter.clothsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cloth, parent, false);
        return new ClothAdapter.clothsViewholder(view);
    }
    @Override
    protected void onBindViewHolder(@NonNull ClothAdapter.clothsViewholder holder, int position, @NonNull Cloth model) {
        holder.age.setText(model.age);
        holder.gender.setText(model.gender);
        holder.season.setText(model.season);
        holder.email.setText(model.Email);
        holder.phone.setText(model.Phone);
        holder.username.setText(model.Username);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age, gender, season, username, email, phone;
                TextView a = v.findViewById(R.id.ages);
                TextView g = v.findViewById(R.id.gender);
                TextView s = v.findViewById(R.id.season);
                TextView un = v.findViewById(R.id.username);
                TextView e = v.findViewById(R.id.email);
                TextView p = v.findViewById(R.id.phone);
                age = a.getText().toString();
                gender = g.getText().toString();
                season = s.getText().toString();
                username = un.getText().toString();
                email = e.getText().toString();
                phone = p.getText().toString();
                Intent intent = new Intent(v.getContext(), ShowClothActivity.class);
                intent.putExtra("Ages",age);
                intent.putExtra("Gender",gender);
                intent.putExtra("Season",season);
                intent.putExtra("Username",username);
                intent.putExtra("Email",email);
                intent.putExtra("Phone",phone);
                v.getContext().startActivity(intent);
            }
        });
    }
    class clothsViewholder extends RecyclerView.ViewHolder {
        TextView age, gender, season, username, email, phone;
        public clothsViewholder(@NonNull View itemView)
        {
            super(itemView);

            age = itemView.findViewById(R.id.ages);
            gender = itemView.findViewById(R.id.gender);
            season = itemView.findViewById(R.id.season);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
