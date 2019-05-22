package com.bignerdranch.android.bqtabs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

import java.util.List;

public class fragmentBurger extends Fragment {
    public static BurgerQueenDBHelper BurgerQueenDB = null;
    public MenuInventory mMenuInventory;
    View v;
    private RecyclerView myrecyclerview;
    private List<MenuItem> mData;


    public fragmentBurger() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.burger_fragment,container,false);
        myrecyclerview = v.findViewById(R.id.burger_recyclerview);
        mMenuInventory = MenuInventory.get(getActivity());
        mData = mMenuInventory.getCategoryMenuItems("Burgers");
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getActivity(),mData);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BurgerQueenDB = new BurgerQueenDBHelper(getActivity());

    }


}
