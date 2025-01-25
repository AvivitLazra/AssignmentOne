package com.example.assimentone.models;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    // This interface serves as onclick listener to define the onclick function to the buttons after inflating them in the activity.
    public interface OnCardClickListener {
        void onCardButtonClick(MiniProduct product, int value, View view);
    }

    private OnCardClickListener listener;


    public Adapter(ArrayList<Animal> productList, OnCardClickListener listener){
        this.productList=productList;
        this.listener= listener; //Setting the onclickListener for the button
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textaType;
        TextView textaDesc;
        ImageView aImage;
        Button addBtn;
        Button removeBtn;
        TextView textAmount;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.CardLayoutRes);
            textaType = itemView.findViewById(R.id.textProductType);
            textaDesc = itemView.findViewById(R.id.textProductDescription);
            textAmount = itemView.findViewById(R.id.amountTextBox);
            aImage = itemView.findViewById(R.id.ProductImage);
            addBtn = itemView.findViewById(R.id.btnAddItem);
            removeBtn = itemView.findViewById(R.id.btnRemoveItem);



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
        Button addBtn = holder.addBtn;
        Button removeBtn = holder.removeBtn;

        textViewCName.setText(productList.get(position).getaType());
        textViewCDesc.setText(productList.get(position).getaDesc());
        imageCharacterImage.setImageResource(productList.get(position).getaImage());
        addBtn.setOnClickListener(v -> {
            if (listener != null){
                MiniProduct miniProduct = new MiniProduct(productList.get(position).getaType(), 1);
                listener.onCardButtonClick(miniProduct, 1, holder.itemView);
            }
        });
        removeBtn.setOnClickListener(v -> {
            if (listener != null){
                MiniProduct miniProduct = new MiniProduct(productList.get(position).getaType(), 1);
                listener.onCardButtonClick(miniProduct, -1, holder.itemView);

            }
        });
        MiniProduct miniProduct = new MiniProduct(productList.get(position).getaType(), 0);
        listener.onCardButtonClick(miniProduct, 0, holder.itemView);


   }

    @Override
    public int getItemCount() {return productList.size();}


}
