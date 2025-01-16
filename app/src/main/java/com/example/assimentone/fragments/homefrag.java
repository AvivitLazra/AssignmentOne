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

import com.example.assimentone.R;
import com.example.assimentone.models.Adapter;
import com.example.assimentone.models.Animal;
import com.example.assimentone.models.Products;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Animal> productList;
    private Adapter adapter;

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

        for(int i = 0; i< Products.productTypes.length; i++){
            Log.d("ItemCollection","Collecting items...");
            productList.add(new Animal(
                    Products.productTypes[i],
                    Products.productImages[i],
                    Products.productDescriptions[i]

            ));
        }

        adapter = new Adapter(productList);
        Log.d("ItemCollection","set........");
        recyclerView.setAdapter(adapter);
        Log.d("ItemCollection","Removing...");

        return view;
    }

}