package com.bignerdranch.android.bqtabs;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

public class RecyclerViewFavouriteAdapter extends RecyclerView.Adapter<RecyclerViewFavouriteAdapter.FavouriteViewHolder> {
    private Context mContext;
    private List<MenuItem> mData;
    private FavouritesBase favourite;
    private int drawableId;


    public RecyclerViewFavouriteAdapter(Context context, List<MenuItem> mData) {
        this.mContext = context;
        this.mData = mData;

    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_favourites,parent,false);
        final RecyclerViewFavouriteAdapter.FavouriteViewHolder vHolder = new RecyclerViewFavouriteAdapter.FavouriteViewHolder(v);
        ImageButton rDeleteButton;

        rDeleteButton = v.findViewById(R.id.rDeleteButton);
        rDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String iD = mData.get(vHolder.getAdapterPosition()).getMenuID();
                int iId = Integer.valueOf(iD);
                favourite = FavouritesBase.get(mContext);
                favourite.deleteFavourite(iId);
                Toast.makeText(mContext,"I am deleting this ",Toast.LENGTH_SHORT).show();
            }
        });

        return vHolder;
    }

    @Override
    public int getItemCount() {
        if (mData != null){
            int r = mData.size();
            String x = String.valueOf(r);
            Log.i("itemsize",x);
            return r;}
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFavouriteAdapter.FavouriteViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_description.setText(mData.get(position).getDescription());
        holder.tv_price.setText(mData.get(position).getPrice());
        try{
            Class res = R.drawable.class;
            Field field = res.getField(mData.get(position).getImage_path());
            drawableId = field.getInt(null);
        }catch (Exception e){
            Log.e("MyTag","Failure to get drawable id",e);
        }
        holder.img.setImageResource(drawableId);

    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_favourites;
        private TextView tv_name;
        private TextView tv_description;
        private TextView tv_price;
        private ImageView img;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);

            item_favourites = itemView.findViewById(R.id.menu_favourites);
            tv_name = itemView.findViewById(R.id.name_item);
            tv_description = itemView.findViewById(R.id.description);
            tv_price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img_menuitem);
        }
    }
}
