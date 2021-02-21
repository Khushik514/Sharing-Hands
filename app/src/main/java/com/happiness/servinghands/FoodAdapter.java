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


public class FoodAdapter extends FirebaseRecyclerAdapter<
        Food, FoodAdapter.foodsViewholder> {
    DatabaseReference mbase;
    public FoodAdapter(
            @NonNull FirebaseRecyclerOptions<Food> options)
    {
        super(options);
    }
    @NonNull
    @Override
    public FoodAdapter.foodsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food, parent, false);
        return new FoodAdapter.foodsViewholder(view);
    }
    @Override
    protected void onBindViewHolder(@NonNull FoodAdapter.foodsViewholder holder, int position, @NonNull Food model) {
        holder.people.setText(model.noOfPeople);
        holder.type.setText(model.type);
        holder.exp.setText(model.expiry);
        holder.email.setText(model.Email);
        holder.phone.setText(model.Phone);
        holder.username.setText(model.Username);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String people, type, expiry, username, email, phone;
                TextView pe = v.findViewById(R.id.people);
                TextView t = v.findViewById(R.id.type);
                TextView ex = v.findViewById(R.id.expiry);
                TextView un = v.findViewById(R.id.username);
                TextView e = v.findViewById(R.id.email);
                TextView p = v.findViewById(R.id.phone);
                people = pe.getText().toString();
                type = t.getText().toString();
                expiry = ex.getText().toString();
                username = un.getText().toString();
                email = e.getText().toString();
                phone = p.getText().toString();
                Intent intent = new Intent(v.getContext(), ShowFoodActivity.class);
                intent.putExtra("No.OfPeople",people);
                intent.putExtra("Type",type);
                intent.putExtra("Expiry",expiry);
                intent.putExtra("Username",username);
                intent.putExtra("Email",email);
                intent.putExtra("Phone",phone);
                v.getContext().startActivity(intent);
            }
        });
    }
    class foodsViewholder extends RecyclerView.ViewHolder {
        TextView people, type, exp, username, email, phone;
        public foodsViewholder(@NonNull View itemView)
        {
            super(itemView);

            people = itemView.findViewById(R.id.people);
            type = itemView.findViewById(R.id.type);
            exp = itemView.findViewById(R.id.expiry);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
