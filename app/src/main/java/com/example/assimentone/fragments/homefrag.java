package com.example.assimentone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assimentone.R;
import com.example.assimentone.models.Adapter;
import com.example.assimentone.models.Animal;
import com.example.assimentone.models.MiniProduct;
import com.example.assimentone.models.Products;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefrag#newInstance} factory method to
 * create an instance of this fragment.
 */

// Implementing Adapter.OnCardClickListener to gain capabilities of the interface listener we wrote in the adapter class.
public class homefrag extends Fragment implements Adapter.OnCardClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Animal> productList;
    private Adapter adapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("users");


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    public homefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static homefrag newInstance(String param1, String param2) {
        homefrag fragment = new homefrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.homefrag, container, false);
        recyclerView = view.findViewById(R.id.recView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        productList = new ArrayList<Animal>();
        //Generating products list for RecycleView Items
        for(int i = 0; i< Products.productTypes.length; i++){
            Log.d("ItemCollection","Collecting items...");
            productList.add(new Animal(
                    Products.productTypes[i],
                    Products.productImages[i],
                    Products.productDescriptions[i]

            ));
        }
        // Since we implement the listener, we set this as the listener of the adapter.
        adapter = new Adapter(productList,this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override public void onCardButtonClick(MiniProduct product) {
        //Getting firebase reference and getting user instance
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid= currentUser.getUid();
        ArrayList<MiniProduct> userProducts = new ArrayList<>();
        DatabaseReference myRefUser = myRef.child(uid);
        //Firebase plug and play code for getting information from the database, making decisions if
        // to create a new item on the database or increment it.
        myRefUser.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //Trying to obtain object from firebase if exists
                MiniProduct value = dataSnapshot.getValue(MiniProduct.class);
                if(value!=null) {
                    //if it exists, we increment its value and override the object in the firebase
                    value.setAmount(value.getAmount() + 1);
                    myRef.child(uid).setValue(value);
                    Toast.makeText(getContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();
                    Log.d("Success", "Product amount updated successfully");
                }
                else{
                    myRef.child(uid).setValue(new MiniProduct(product.getaType(), 1));
                    Toast.makeText(getContext(), "Item added!", Toast.LENGTH_SHORT).show();
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








}