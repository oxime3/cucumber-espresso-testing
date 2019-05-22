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

public class fragmentFavorites extends Fragment {
    public static BurgerQueenDBHelper BurgerQueenDB = null;
    public FavouritesBase mFavouritesBase;
    View v;
    private RecyclerView favouriterecyclerview;
    private List<MenuItem> mData;



    public fragmentFavorites() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.favorites_fragment,container,false);
        favouriterecyclerview = v.findViewById(R.id.favorites_recyclerview);
        mFavouritesBase = FavouritesBase.get(getActivity());
        mData = mFavouritesBase.getFavouriteMenuItems();
        RecyclerViewFavouriteAdapter recyclerAdapter = new RecyclerViewFavouriteAdapter(getActivity(),mData);
        favouriterecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        favouriterecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BurgerQueenDB = new BurgerQueenDBHelper(getActivity());

    }


}
