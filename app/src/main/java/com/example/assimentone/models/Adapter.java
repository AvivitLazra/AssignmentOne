package com.example.assimentone.models;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assimentone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Animal> productList;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("users");






    public Adapter(ArrayList<Animal> productList){
        this.productList=productList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textaType;
        TextView textaDesc;
        ImageView aImage;
        Button addBtn;
        Button removeBtn;
        private int amount = 0;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.CardLayoutRes);
            textaType = itemView.findViewById(R.id.textProductType);
            textaDesc = itemView.findViewById(R.id.textProductDescription);
            aImage = itemView.findViewById(R.id.ProductImage);
            Button addBtn = itemView.findViewById(R.id.btnAddItem);
            Button removeBtn = itemView.findViewById(R.id.btnRemoveItem);



        }


    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position){
        TextView textViewCName = holder.textaType;
        TextView textViewCDesc = holder.textaDesc;
        ImageView imageCharacterImage = holder.aImage;

        textViewCName.setText(productList.get(position).getaType());
        textViewCDesc.setText(productList.get(position).getaDesc());
        imageCharacterImage.setImageResource(productList.get(position).getaImage());
        if (holder.addBtn !=null ){
            holder.addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String uid= currentUser.getUid();
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            MiniProduct value = dataSnapshot.getValue(MiniProduct.class);
                            if(value!=null) {
                                holder.amount = value.getAmount()+1;
                                value.setAmount(holder.amount);
                                myRef.child(uid).setValue(value);
                            }
                            else{
                                myRef.child(uid).setValue(new MiniProduct(textViewCName.getText().toString(),1 ));
                            }
                            Log.d("tag", "Value is: " + value);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("tag", "Failed to read value.", error.toException());
                        }
                    });

                }
            });
        }


    }

    @Override
    public int getItemCount() {return productList.size();}


}
