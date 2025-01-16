package com.example.assimentone.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assimentone.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Animal> productList;


    public Adapter(ArrayList<Animal> productList){
        this.productList=productList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textaType;
        TextView textaDesc;
        ImageView aImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.CardLayoutRes);
            textaType = itemView.findViewById(R.id.textProductType);
            textaDesc = itemView.findViewById(R.id.textProductDescription);
            aImage = itemView.findViewById(R.id.ProductImage);
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

    }

    @Override
    public int getItemCount() {return productList.size();}
}
