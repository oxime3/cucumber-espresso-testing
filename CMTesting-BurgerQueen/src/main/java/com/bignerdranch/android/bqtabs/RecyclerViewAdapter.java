package com.bignerdranch.android.bqtabs;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<MenuItem> mData;
    private Dialog myDialog;
    private FavouritesBase favourite;
    private OrderList mOrderList;
    private int drawableId;


    public RecyclerViewAdapter(Context context, List<MenuItem> mData) {
        this.mContext = context;
        this.mData = mData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_menu,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        ImageButton rAddButton ;
        ImageButton rFavButton;



        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_item);

        rAddButton = v.findViewById(R.id.rAddButton);
        rAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String iD = mData.get(vHolder.getAdapterPosition()).getMenuID();
                int iId = Integer.valueOf(iD);
                mOrderList = mOrderList.get(mContext);
                mOrderList.addOrderItem(iId);
                Toast.makeText(mContext,"Adding to orders ",Toast.LENGTH_SHORT).show();
            }
        });

        rFavButton = v.findViewById(R.id.rFavButton);
        rFavButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String iD = mData.get(vHolder.getAdapterPosition()).getMenuID();
                int iId = Integer.valueOf(iD);
                favourite = FavouritesBase.get(mContext);
                favourite.addFavourite(iId);
                Toast.makeText(mContext,"Adding to favourites",Toast.LENGTH_SHORT).show();
            }
        });


        vHolder.item_menu.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                TextView dialog_item_tv = myDialog.findViewById(R.id.dialog_menuitem);
                TextView dialog_description_tv = myDialog.findViewById(R.id.dialog_description);
                ImageView dialog_img = myDialog.findViewById(R.id.dialog_img);
                dialog_item_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_description_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescription());
                try{
                    Class res = R.drawable.class;
                    Field field = res.getField(mData.get(vHolder.getAdapterPosition()).getImage_path());
                    drawableId = field.getInt(null);
                }catch (Exception e){
                    Log.e("MyTag","Failure to get drawable id",e);
                }
                dialog_img.setImageResource(drawableId);
                myDialog.show();
                Button addBtn = myDialog.findViewById(R.id.dialogBtnCart);
                Button favBtn = myDialog.findViewById(R.id.dialogBtnFav);

                addBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String iD = mData.get(vHolder.getAdapterPosition()).getMenuID();
                        int iId = Integer.valueOf(iD);
                        mOrderList = mOrderList.get(mContext);
                        mOrderList.addOrderItem(iId);
                        Toast.makeText(mContext,"Testing",Toast.LENGTH_SHORT).show();
                    }
                });

                favBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String iD = mData.get(vHolder.getAdapterPosition()).getMenuID();
                        int iId = Integer.valueOf(iD);
                        favourite = FavouritesBase.get(mContext);
                        favourite.addFavourite(iId);
                        Toast.makeText(mContext,"TESTING BUTTON 1",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return vHolder;

    }

    //@Override
    public int getItemCount() {
        if (mData != null){
            int r = mData.size();
            String x = String.valueOf(r);
            Log.i("itemsize",x);
            return r;}
        return 0;
    }

    public void setData(List<MenuItem> menuItems){
        mData = menuItems;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_description.setText(mData.get(position).getDescription());
        holder.tv_price.setText(mData.get(position).getPrice());
        try{
            Class res = R.drawable.class;
            Field field = res.getField(mData.get(position).getImage_path());
            drawableId = field.getInt(null);
            Log.i("ImagePath",mData.get(position).getImage_path());
        }catch (Exception e){
            Log.e("MyTag","Failure to get drawable id",e);
        }
        holder.img.setImageResource(drawableId);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_menu;
        private TextView tv_name;
        private TextView tv_description;
        private TextView tv_price;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_menu = itemView.findViewById(R.id.menu_id);
            tv_name = itemView.findViewById(R.id.name_item);
            tv_description = itemView.findViewById(R.id.description);
            tv_price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img_menuitem);
        }
    }


}
